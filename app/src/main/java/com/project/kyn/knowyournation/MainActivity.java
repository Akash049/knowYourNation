package com.project.kyn.knowyournation;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.project.kyn.knowyournation.adapters.CountryAdapter;
import com.project.kyn.knowyournation.apiInterface.APIClient;
import com.project.kyn.knowyournation.apiInterface.ApiInterface;
import com.project.kyn.knowyournation.listener.RecyclerTouchListener;
import com.project.kyn.knowyournation.models.Country;


import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private List<Country> countryList = new ArrayList<>();
    private List<Country> rosterData = new ArrayList<>();
    private EditText searchNation;
    private RecyclerView recyclerView;
    private CountryAdapter countryAdapter;
    private Button reconnect;
    private TextView sampleData;
    private ImageView advSearch;
    private ProgressBar progressBar;

    //Database variables
    private Nitrite db;
    private ObjectRepository<Country> repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        sampleData = (TextView)findViewById(R.id.sampleText);
        searchNation = (EditText)findViewById(R.id.search);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        advSearch = (ImageView)findViewById(R.id.adv_search);
        reconnect = (Button)findViewById(R.id.reconnect);
        countryAdapter = new CountryAdapter(countryList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(countryAdapter);

        //Check if the data is already present or not
        try{
            //This will create a database or if present then simply access it.
            db = Nitrite.builder()
                    .compressed()
                    .filePath(getFilesDir().getPath() +"/kyn.db")
                    .openOrCreate("kyn_user", "kyn_user");

            Toast.makeText(getApplicationContext(),getFilesDir().getPath().toString(),Toast.LENGTH_LONG).show();

            // Create or Access the Object Repository
            repository = db.getRepository(Country.class);

            rosterData = repository.find().toList();

            if(rosterData.size() > 0){

                for(int i = 0;i < rosterData.size() ; i++){
                    countryList.add(rosterData.get(i));
                }

                //making the progressbar visible
                progressBar.setVisibility(View.GONE);

                countryAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"Loading data from DB",Toast.LENGTH_SHORT).show();
                Log.d("MAIN_ACTIVITY","Data already present.");
            }else{
                //Initiating Data Download
                if(isNetworkAvailable()){

                    Toast.makeText(getApplicationContext(),"Initiating the Data download",Toast.LENGTH_SHORT).show();
                    Log.d("MAIN_ACTIVITY","Initiating the Data download.");
                    loadContent();
                }else {
                    progressBar.setVisibility(View.GONE);
                    reconnect.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"Please connect to the internet!!!",Toast.LENGTH_SHORT).show();
                }
            }

        }catch (Exception e){

            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();

        }

        //This takes to advance search
        advSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AdvanceSearch.class));
            }
        });

        reconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNetworkAvailable()){
                    reconnect.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Initiating the Data download",Toast.LENGTH_SHORT).show();
                    Log.d("MAIN_ACTIVITY","Initiating the Data download.");
                    loadContent();
                }else {

                    Toast.makeText(getApplicationContext(),"Please connect to the internet!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Country country = countryList.get(position);
                //Toast.makeText(getApplicationContext(), country.getName() + " is selected!", Toast.LENGTH_SHORT).show();

                try{

                    startActivity(new Intent(getApplicationContext(),DetailActivity.class)
                            .putExtra("DATA",country));

                }catch (Exception e){
                    //Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                    Log.d("PARSE_ERROR",e.toString());
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        searchNation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //making the progressbar visible
                progressBar.setVisibility(View.VISIBLE);

                countryList.clear();

                for(int j =0 ; j < rosterData.size() ; j++){
                    if(rosterData.get(j).getName().toUpperCase().indexOf(charSequence.toString().toUpperCase()) != -1){
                        countryList.add(rosterData.get(j));
                    }
                }
                //Create an adapter using this list and display
                countryAdapter.notifyDataSetChanged();
                //making the progressbar visible
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void loadContent() {

        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        ApiInterface apiService =
                APIClient.getClient().create(ApiInterface.class);

        //Toast.makeText(getApplicationContext(),"Data downloaded initiated",Toast.LENGTH_SHORT).show();

        Call<List<Country>> call = apiService.getData();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, retrofit2.Response<List<Country>> response) {

                //making the progressbar visible
                progressBar.setVisibility(View.GONE);

                countryList.clear();

                try{
                    //Setting up the data points
                    for(int i=0;i<response.body().size();i++){
                        countryList.add(response.body().get(i));
                        rosterData.add(response.body().get(i));

                        //Added to the database
                        repository.insert(response.body().get(i));
                    }
                    countryAdapter.notifyDataSetChanged();
                    Log.d("DATA Downloaded",String.valueOf(countryList.size()));

                }catch (Exception e){
                    Log.d("Data Parsing Failure",e.toString());

                }

            }
            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.d("Download failure",t.toString());

            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
