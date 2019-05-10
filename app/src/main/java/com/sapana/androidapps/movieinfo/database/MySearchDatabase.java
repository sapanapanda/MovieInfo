package com.sapana.androidapps.movieinfo.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {MySearch.class}, version = 1, exportSchema = false)
public abstract class MySearchDatabase extends RoomDatabase {
    public abstract MySearchDao mySearchDao();
}
