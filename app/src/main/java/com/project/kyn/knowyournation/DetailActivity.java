package com.project.kyn.knowyournation;

import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;


import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.project.kyn.knowyournation.adapters.InformationAdapter;
import com.project.kyn.knowyournation.models.Country;
import com.project.kyn.knowyournation.models.Currencies;
import com.project.kyn.knowyournation.models.Language;
import com.project.kyn.knowyournation.models.RegionalBlock;
import com.project.kyn.knowyournation.models.Translation;
import com.project.kyn.knowyournation.util.SvgDecoder;
import com.project.kyn.knowyournation.util.SvgDrawableTranscoder;
import com.project.kyn.knowyournation.util.SvgSoftwareLayerSetter;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.dizitart.no2.objects.filters.ObjectFilters.and;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

/**
 * Created by akashchandra on 11/23/17.
 */

public class DetailActivity extends AppCompatActivity {

    private List<String> headList = new ArrayList<>();
    private List<String> infoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ImageView flagView;
    private InformationAdapter informationAdapter;

    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;

    //Database variables
    private Nitrite db;
    private ObjectRepository<Country> repository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        flagView = (ImageView)findViewById(R.id.flag);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        informationAdapter = new InformationAdapter(headList,infoList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(informationAdapter);

        try{
            Intent intent = getIntent();
            Country country = (Country)intent.getSerializableExtra("DATA");
            //Toast.makeText(getApplicationContext(),"Data captured is "+ country.getCapital(),Toast.LENGTH_LONG).show();

            setTitle(country.getName());


            requestBuilder = Glide.with(getApplicationContext())
                    .using(Glide.buildStreamModelLoader(Uri.class, getApplicationContext()), InputStream.class)
                    .from(Uri.class)
                    .as(SVG.class)
                    .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                    .sourceEncoder(new StreamEncoder())
                    .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                    .decoder(new SvgDecoder())
                    .placeholder(R.drawable.ic_flag_black_24dp)
                    .error(R.drawable.ic_flag_black_24dp)
                    .animate(android.R.anim.fade_in)
                    .listener(new SvgSoftwareLayerSetter<Uri>());

            Uri uri = Uri.parse(country.getFlag());

            requestBuilder
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    // SVG cannot be serialized so it's not worth to cache it
                    .load(uri)
                    .into(flagView);

            //Setting up the data for Display
            headList.add("Capital");
            infoList.add(country.getCapital());

            headList.add("Top Level Domain");
            List<String> tld = country.getTopLevelDomain();
            String tldString = "";
            for(int i = 0;i<tld.size() ; i++){
                if(i!=tld.size()-1){
                    tldString += tld.get(i) + " , ";
                }else {
                    tldString += tld.get(i);
                }
            }
            infoList.add(tldString);

            headList.add("Alpha2Code");
            infoList.add(country.getAlpha2Code());

            headList.add("Alpha3Code");
            infoList.add(country.getAlpha3Code());

            headList.add("Calling Codes");
            List<String> cc = country.getCallingCodes();
            String ccString = "";
            for(int i = 0;i<cc.size() ; i++){
                if(i!=cc.size()-1){
                    ccString += cc.get(i) + " , ";
                }else {
                    ccString += cc.get(i);
                }
            }
            infoList.add(ccString);

            headList.add("Alternate Spellings");
            List<String> as = country.getAltSpellings();
            String asString = "";
            for(int i = 0;i<as.size() ; i++){
                if(i!=as.size()-1){
                    asString += as.get(i) + " , ";
                }else {
                    asString += as.get(i);
                }
            }
            infoList.add(asString);

            headList.add("Region");
            infoList.add(country.getRegion());

            headList.add("Sub Region");
            infoList.add(country.getSubregion());

            headList.add("Population");
            infoList.add(country.getPopulation().toString());

            headList.add("Latitude Longitude");
            infoList.add("Latitude = "+country.getLatlng().get(0) + " ; Longitude = "+country.getLatlng().get(1));

            headList.add("Demonym");
            infoList.add(country.getDemonym());

            headList.add("Area");
            infoList.add(String.valueOf(country.getArea()));

            headList.add("Gini");
            infoList.add(String.valueOf(country.getGini()));

            headList.add("Timezones");
            List<String> timezone = country.getTimezones();
            String tzString = "";
            for(int i = 0;i<timezone.size() ; i++){
                if(i!=timezone.size()-1){
                    tzString += timezone.get(i) + " , ";
                }else {
                    tzString += timezone.get(i);
                }
            }
            infoList.add(tzString);

            headList.add("Borders");
            List<String> border = country.getBorders();
            String bString = "";
            for(int i = 0;i<border.size() ; i++){
                if(i!=border.size()-1){
                    bString += border.get(i) + " , ";
                }else {
                    bString += border.get(i);
                }
            }
            infoList.add(bString);

            headList.add("Native Name");
            infoList.add(country.getNativeName());

            headList.add("Numeric Code");
            infoList.add(String.valueOf(country.getNumericCode()));

            List<Currencies> currencies = country.getCurrencies();
            if(currencies.size() == 1){

                headList.add("Currency : Code");
                infoList.add(currencies.get(0).getCode());

                headList.add("Currency : Name");
                infoList.add(currencies.get(0).getName());

                headList.add("Currency : Symbol");
                infoList.add(currencies.get(0).getSymbol());

            }else if(currencies.size() > 1){

                for(int i = 0;i<currencies.size();i++){

                    headList.add("Currency-"+(i+1)+" : Code");
                    infoList.add(currencies.get(i).getCode());

                    headList.add("Currency-"+(i+1)+" : Name");
                    infoList.add(currencies.get(i).getName());

                    headList.add("Currency-"+(i+1)+" : Symbol");
                    infoList.add(currencies.get(i).getSymbol());

                }

            }

            List<Language> languages = country.getLanguages();
            if(languages.size() == 1){
                headList.add("Language : iso639_1");
                infoList.add(languages.get(0).getIso639_1());

                headList.add("Language : iso639_2");
                infoList.add(languages.get(0).getIso639_2());

                headList.add("Language : name");
                infoList.add(languages.get(0).getName());

                headList.add("Language : native name");
                infoList.add(languages.get(0).getNativeName());
            }else if(languages.size() > 1){
                for(int i = 0 ;i<languages.size();i++){

                    headList.add("Language-"+(i+1)+" : iso639_1");
                    infoList.add(languages.get(i).getIso639_1());

                    headList.add("Language-"+(i+1)+" : iso639_2");
                    infoList.add(languages.get(i).getIso639_2());

                    headList.add("Language-"+(i+1)+" : name");
                    infoList.add(languages.get(i).getName());

                    headList.add("Language-"+(i+1)+" :native name");
                    infoList.add(languages.get(i).getNativeName());
                }
            }

            //Setting up the translation
            Translation translation = country.getTranslation();
            headList.add("Translation : de");
            infoList.add(translation.getDe());

            headList.add("Translation : es");
            infoList.add(translation.getEs());

            headList.add("Translation : fr");
            infoList.add(translation.getFr());

            headList.add("Translation : js");
            infoList.add(translation.getJa());

            headList.add("Translation : it");
            infoList.add(translation.getIt());

            headList.add("Translation : br");
            infoList.add(translation.getBr());

            headList.add("Translation : pt");
            infoList.add(translation.getPt());

            headList.add("Translation : nl");
            infoList.add(translation.getNl());

            headList.add("Translation : hr");
            infoList.add(translation.getHr());

            headList.add("Translation : fa");
            infoList.add(translation.getFa());

            //Setting up the regional Block
            List<RegionalBlock> regionalBlockList = country.getRegionalBlocks();
            if(regionalBlockList.size() == 1){

                headList.add("Regional Blocs : Acronym");
                infoList.add(regionalBlockList.get(0).getAcronym());

                headList.add("Regional Blocs : Name");
                infoList.add(regionalBlockList.get(0).getName());

                headList.add("Regional Blocs : OtherAcronyms");
                List<String> otherAc = regionalBlockList.get(0).getOtherAcronyms();
                String oacString = "";
                for(int i = 0;i<otherAc.size() ; i++){
                    if(i!=otherAc.size()-1){
                        oacString += otherAc.get(i) + " , ";
                    }else {
                        oacString += otherAc.get(i);
                    }
                }
                infoList.add(oacString);

                headList.add("Regional Blocs : Other Names");
                List<String> otherNm = regionalBlockList.get(0).getOtherAcronyms();
                String onmString = "";
                for(int i = 0;i<otherNm.size() ; i++){
                    if(i!=otherNm.size()-1){
                        onmString += otherNm.get(i) + " , ";
                    }else {
                        onmString += otherNm.get(i);
                    }
                }
                infoList.add(onmString);
            }else if(regionalBlockList.size() > 1){

                for(int j =0 ; j < regionalBlockList.size();j++){

                    headList.add("Regional Blocs : Acronym");
                    infoList.add(regionalBlockList.get(j).getAcronym());

                    headList.add("Regional Blocs : Name");
                    infoList.add(regionalBlockList.get(j).getName());

                    headList.add("Regional Blocs : OtherAcronyms");
                    List<String> otherAc = regionalBlockList.get(j).getOtherAcronyms();
                    String oacString = "";
                    for(int i = 0;i<otherAc.size() ; i++){
                        if(i!=otherAc.size()-1){
                            oacString += otherAc.get(i) + " , ";
                        }else {
                            oacString += otherAc.get(i);
                        }
                    }
                    infoList.add(oacString);

                    headList.add("Regional Blocs : Other Names");
                    List<String> otherNm = regionalBlockList.get(j).getOtherAcronyms();
                    String onmString = "";
                    for(int i = 0;i<otherNm.size() ; i++){
                        if(i!=otherNm.size()-1){
                            onmString += otherNm.get(i) + " , ";
                        }else {
                            onmString += otherNm.get(i);
                        }
                    }
                    infoList.add(onmString);

                }

            }

            headList.add("Cioc");
            infoList.add(country.getCioc());

            informationAdapter.notifyDataSetChanged();
        }catch (Exception e){
            //Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }

    }
}
