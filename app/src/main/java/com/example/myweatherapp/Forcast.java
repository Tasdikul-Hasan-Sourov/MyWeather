package com.example.myweatherapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Forcast extends AppCompatActivity {
    TextView topText,temp;
    RecyclerView recView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forcast);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        temp=(TextView) findViewById(R.id.tempFor);
        topText=(TextView) findViewById(R.id.topText);
        recView=(RecyclerView) findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this));
        displayData();
        getForcast(topText.getText().toString().trim());
    }

    private void getForcast(String name) {
        CityApi cityApi= ApiClient.getClient().create(CityApi.class);
        Call<Example2> call=  cityApi.getForcast(name);
        call.enqueue(new Callback<Example2>() {
            @Override
            public void onResponse(Call<Example2> call, Response<Example2> response) {

                Example2 list=response.body();
                recView.setAdapter(new RecAdapter(Forcast.this,list.getList()));

            }

            @Override
            public void onFailure(Call<Example2> call, Throwable t) {

            }
        });



    }

    private void displayData() {
        SharedPreferences sp= getSharedPreferences("city", Context.MODE_PRIVATE);
        String city=sp.getString("city","").toUpperCase();
        topText.setText(city);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}