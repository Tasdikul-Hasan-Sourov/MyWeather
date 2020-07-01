package com.example.myweatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {
    @SerializedName("main")
    private Main main;
    @SerializedName("weather")
    private List<MyWeather> list;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<MyWeather> getList() {
        return list;
    }
}
