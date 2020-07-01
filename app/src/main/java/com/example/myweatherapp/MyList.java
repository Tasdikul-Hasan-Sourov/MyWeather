package com.example.myweatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyList {
    @SerializedName("main")
    private Main main;
    @SerializedName("dt_txt")
    String date;
    @SerializedName("weather")
    private List<MyWeather> list;

    public Main getMain() {
        return main;
    }

    public String getDate() {
        return date;
    }

    public List<MyWeather> getList() {
        return list;
    }
}

