package com.example.myweatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Example2 {
    @SerializedName("list")
  private List<MyList> list;

    public List<MyList> getList() {
        return list;
    }
}
