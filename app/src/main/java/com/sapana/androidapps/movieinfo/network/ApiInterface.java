package com.sapana.androidapps.movieinfo.network;

import com.sapana.androidapps.movieinfo.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("/")
    Call<Movie>getMovie(@Query("apikey") String key,
                        @Query("t") String title,@Query("type") String type, @Query("y") String year,
            @Query("plot") String plot);
    }