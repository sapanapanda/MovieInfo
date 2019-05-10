package com.sapana.androidapps.movieinfo.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MySearchDao {

    @Query("SELECT * FROM MySearch")
    List<MySearch> getAll();

    @Insert
    void insert(MySearch mySearch);
}
