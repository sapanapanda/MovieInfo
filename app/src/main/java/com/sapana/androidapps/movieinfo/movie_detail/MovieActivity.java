package com.sapana.androidapps.movieinfo.movie_detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.sapana.androidapps.movieinfo.R;
import com.sapana.androidapps.movieinfo.adapter.MovieRecyclerViewAdapter;
import com.sapana.androidapps.movieinfo.database.MySearch;
import com.sapana.androidapps.movieinfo.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity implements movieContract.View {
    moviePresenter presenter;

    RecyclerView rvList;

    ArrayList<Movie> movieList;
    MovieRecyclerViewAdapter adapter;
    MySearch search;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = findViewById(R.id.progress);
        presenter = new moviePresenter(this);
        search = getIntent().getParcelableExtra("Movie");


        //  FloatingActionButton fab = findViewById(R.id.fab);
        //     progressBar = findViewById(R.id.progress);
        rvList = findViewById(R.id.rvList);

        movieList = new ArrayList<>();
        adapter = new MovieRecyclerViewAdapter(this, movieList);

        LinearLayoutManager mLayoutManager
                = new LinearLayoutManager(this);
        rvList.setLayoutManager(mLayoutManager);

        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.setAdapter(adapter);
        presenter.requestData(search.getTitle(), search.getYear(), search.getPlot(), search.getType());

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void setDataToRecyclerView(List<Movie> movieList) {

        if (movieList.size() == 0)
            Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_SHORT).show();
        else {
            this.movieList.addAll(movieList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();
    }
}
