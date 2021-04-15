package com.sata.katalogfilem.Retrofit;

import com.sata.katalogfilem.Model.MovieListModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET("?apikey=b45dad4f&s=iron") Call<MovieListModel> getData();

}
