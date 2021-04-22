package com.sata.katalogfilem.Retrofit;

import com.sata.katalogfilem.Model.MovieDetailModel;
import com.sata.katalogfilem.Model.MovieListModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiEndpoint {
    @GET("/") Call<MovieListModel> getData(
            @QueryMap Map<String, String> options
    );

    @GET("/") Call<MovieDetailModel> geDataDetail(
            @QueryMap Map<String, String> options
    );

}
