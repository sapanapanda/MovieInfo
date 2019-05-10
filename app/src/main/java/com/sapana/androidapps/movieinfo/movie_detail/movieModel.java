package com.sapana.androidapps.movieinfo.movie_detail;

import android.util.Log;
import com.sapana.androidapps.movieinfo.model.Movie;
import com.sapana.androidapps.movieinfo.network.ApiClient;
import com.sapana.androidapps.movieinfo.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

import static android.support.constraint.Constraints.TAG;
import static com.sapana.androidapps.movieinfo.network.ApiClient.API_KEY;

public class movieModel implements movieContract.Model {
    @Override
    public void getMovieList(final OnFinishedListener onFinishedListener, String title, String year, String plot, String type) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Movie> call = apiService.getMovie(API_KEY, title,type,year,plot);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                //  Log.d(TAG, "Data received: " + response.body().toString());
                onFinishedListener.onFinished(movie);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }
    }

