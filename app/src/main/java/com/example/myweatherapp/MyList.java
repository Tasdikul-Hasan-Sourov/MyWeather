package com.example.myweatherapp;

import com.google.gson.annotations.SerializedName;

public class MyList {
    @SerializedName("main")
private Main main;

    public Main getMain() {
        return main;
    }
}

