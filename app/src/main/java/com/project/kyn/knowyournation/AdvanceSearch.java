package com.project.kyn.knowyournation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.kyn.knowyournation.adapters.CountryAdapter;
import com.project.kyn.knowyournation.listener.RecyclerTouchListener;
import com.project.kyn.knowyournation.models.Country;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.ArrayList;
import java.util.List;

import static org.dizitart.no2.objects.filters.ObjectFilters.and;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

/**
 * Created by akashchandra on 11/29/17.
 */

public class AdvanceSearch extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinner;
    private EditText search;
    private Button initSearch;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private CountryAdapter countryAdapter;
    public String query,searchText;

    //Database variables
    private Nitrite db;
    private ObjectRepository<Country> repository;

    private List<Country> countries = new ArrayList<>();
    private List<Country> s = new ArrayList<>();
   

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv_search);
        setTitle("Advance Search");

        //Default search query is capital
        query = "capital";

        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner);
        search = (EditText)findViewById(R.id.search);
        initSearch = (Button)findViewById(R.id.init_search);
        progressBar = (ProgressBar)findViewById(R.id.progress);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        countryAdapter = new CountryAdapter(s,getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(countryAdapter);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> categories = new ArrayList<>();
        categories.add("capital");
        categories.add("region");
        categories.add("subregion");
        categories.add("topLevelDomain");
        categories.add("alpha2Code");
        categories.add("alpha3Code");
        categories.add("cioc");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        initSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initSearch.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                searchText = search.getText().toString();

                //Empty the list
                countries.clear();
                s.clear();

                if(searchText.equals("")){

                    progressBar.setVisibility(View.GONE);
                    initSearch.setVisibility(View.VISIBLE);

                    Toast.makeText(getApplicationContext(),"Please enter a search text !!! ",Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(getApplicationContext(),"Fetching data from Database.",Toast.LENGTH_SHORT).show();

                    try{

                        db = Nitrite.builder()
                                .compressed()
                                .filePath(getFilesDir().getPath() +"/kyn.db")
                                .openOrCreate("kyn_user", "kyn_user");

                        // Create or Access the Object Repository
                        repository = db.getRepository(Country.class);

                        countries = repository.find(
                                and(
                                       eq(query,searchText)
                                )
                        ).toList();

                        if(countries.size() > 0){

                            for(int i = 0;i < countries.size() ; i++){
                                s.add(countries.get(i));
                            }

                            countryAdapter.notifyDataSetChanged();

                            progressBar.setVisibility(View.GONE);
                            initSearch.setVisibility(View.VISIBLE);

                            Toast.makeText(getApplicationContext(),"Data loaded. Data size : "+s.size(),Toast.LENGTH_SHORT).show();
                            //countryAdapter.notifyDataSetChanged();

                        }else{
                            progressBar.setVisibility(View.GONE);
                            initSearch.setVisibility(View.VISIBLE);

                            Toast.makeText(getApplicationContext(),"Data does not exist. Please check and enter again.",Toast.LENGTH_SHORT).show();
                        }

                    }catch (Exception e){

                        progressBar.setVisibility(View.GONE);
                        initSearch.setVisibility(View.VISIBLE);

                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                        Log.d("ADV_SEARCH",e.toString());
                        Toast.makeText(getApplicationContext(),"Could not fetch data. Please try again.",Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Country country = s.get(position);

                try{

                    startActivity(new Intent(getApplicationContext(),DetailActivity.class)
                            .putExtra("DATA",country));

                }catch (Exception e){

                    Log.d("PARSE_ERROR",e.toString());
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        // On selecting a spinner item
        query = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
