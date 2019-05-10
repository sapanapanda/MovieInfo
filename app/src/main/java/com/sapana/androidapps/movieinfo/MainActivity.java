package com.sapana.androidapps.movieinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.sapana.androidapps.movieinfo.adapter.MovieRecyclerViewAdapter;
import com.sapana.androidapps.movieinfo.database.DatabaseClient;
import com.sapana.androidapps.movieinfo.database.MySearch;
import com.sapana.androidapps.movieinfo.model.Movie;
import com.sapana.androidapps.movieinfo.movie_detail.MovieActivity;
import com.sapana.androidapps.movieinfo.movie_detail.movieContract;
import com.sapana.androidapps.movieinfo.movie_detail.moviePresenter;
import com.sapana.androidapps.movieinfo.mysearch.MySearchActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    EditText etTitle,etYear;

    Button btnSearch;
Spinner spPlot,spType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etTitle = findViewById(R.id.etTitle);

        etYear =findViewById(R.id.etYear);



        btnSearch = findViewById(R.id.btnSearch);

        spPlot = findViewById(R.id.spinnerPlot);
        spType =findViewById(R.id.spinnerType);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              if(etTitle.getText().toString().length() > 0) {
                  saveData(new MySearch(etTitle.getText().toString(), etYear.getText().toString(), spPlot.getSelectedItem().toString()
                          , spType.getSelectedItem().toString()));

                  MySearch movie = new MySearch(etTitle.getText().toString(), etYear.getText().toString(), spPlot.getSelectedItem().toString()
                          , spType.getSelectedItem().toString());
                  Intent intent = new Intent(getApplicationContext(), MovieActivity.class);
                  intent.putExtra("Movie", movie);
                  startActivity(intent);
              }else
              {Toast.makeText(getApplicationContext(),"Please enter title to search",Toast.LENGTH_SHORT).show();}
            }
        });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.share_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mysearch) {



            Intent intent = new Intent(getApplicationContext(), MySearchActivity.class);

            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    public  void saveData(final MySearch mySearch)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .mySearchDao()
                        .insert(mySearch);


            }
        }).start();

    }
}
