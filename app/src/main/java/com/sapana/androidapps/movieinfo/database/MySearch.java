package com.sapana.androidapps.movieinfo.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class MySearch implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    protected MySearch(Parcel in) {
        title = in.readString();
        year = in.readString();
        plot = in.readString();
        type = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeString(year);
        dest.writeString(plot);
        dest.writeString(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MySearch> CREATOR = new Creator<MySearch>() {
        @Override
        public MySearch createFromParcel(Parcel in) {
            return new MySearch(in);
        }

        @Override
        public MySearch[] newArray(int size) {
            return new MySearch[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getYear() {
        return year;
    }

    public String getPlot() {
        return plot;
    }

    public String getType() {
        return type;
    }

    public MySearch(String title, String year, String plot, String type) {
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.type = type;
    }

    private String title;
    private String year;
    private String plot;
    private String type;


}
