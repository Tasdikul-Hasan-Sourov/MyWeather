package com.example.myweatherapp;

import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;

public class MyWeather {
    @SerializedName("main")
    String mainLine;
    @SerializedName("description")
    String description;
    @SerializedName("icon")
    String icon;

    public String getMainLine() {
        return mainLine;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
