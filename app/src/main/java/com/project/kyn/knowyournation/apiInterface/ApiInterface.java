package com.project.kyn.knowyournation.apiInterface;

import com.project.kyn.knowyournation.models.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by akashchandra on 11/23/17.
 */

public interface ApiInterface {

    @GET("all")
    Call<List<Country>> getData();

}
