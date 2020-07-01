package com.example.myweatherapp;

import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    String temp;
    @SerializedName("feels_like")
    String feelsLike;
    @SerializedName("humidity")
    String humidity;
    @SerializedName("pressure")
    String pressure;
    @SerializedName("temp_min")
    String temp_min;
    @SerializedName("temp_max")
    String temp_max;

    public String getTemp_min() {
        return temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
}
