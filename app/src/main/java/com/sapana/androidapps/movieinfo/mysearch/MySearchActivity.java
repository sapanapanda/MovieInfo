package com.sapana.androidapps.movieinfo.mysearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import com.sapana.androidapps.movieinfo.R;
import com.sapana.androidapps.movieinfo.adapter.MovieRecyclerViewAdapter;
import com.sapana.androidapps.movieinfo.adapter.SearchRecyclerViewAdapter;
import com.sapana.androidapps.movieinfo.database.DatabaseClient;
import com.sapana.androidapps.movieinfo.database.MySearch;

import java.util.ArrayList;
import java.util.List;

public class MySearchActivity extends AppCompatActivity {

    RecyclerView rvList;
    List<MySearch> searchList;
    SearchRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvList = (RecyclerView)findViewById(R.id.rvList);

        searchList = new ArrayList<>();


        LinearLayoutManager mLayoutManager
                = new LinearLayoutManager(this);
        rvList.setLayoutManager(mLayoutManager);

        rvList.setItemAnimator(new DefaultItemAnimator());


        getDataFromDb();



    }
    public void getDataFromDb(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                searchList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .mySearchDao()
                        .getAll();


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(searchList==null)
                            Toast.makeText(getApplicationContext(),"No results found",Toast.LENGTH_SHORT).show();
                        else{
                            adapter = new SearchRecyclerViewAdapter(MySearchActivity.this, searchList);
                            rvList.setAdapter(adapter);
                    }}
                });
            }
        }).start();

    }
}
