package com.example.myweatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Search extends AppCompatActivity {

    Button bearch,add;
    TextView temp, desc,humidity;
    EditText bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        bearch=(Button) findViewById(R.id.button);
        temp=(TextView) findViewById(R.id.temp);
        desc =(TextView) findViewById(R.id.desc);
        humidity =(TextView) findViewById(R.id.humidity);
        bar=(EditText) findViewById(R.id.search);
        add=(Button) findViewById(R.id.addMain);

        bearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCity(bar.getText().toString().trim());
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(getApplicationContext(),MainActivity.class);
                 saveInfo();
                startActivity(m);
            }
        });

    }

    private void  getCity(String name){
        CityApi apiInterface= ApiClient.getClient().create(CityApi.class);
        Call<Example> call=  apiInterface.getCity(name);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                temp.setText("টেম্পারেচার" +" "+response.body().getMain().getTemp());
                desc.setText("Pressure :"+" "+response.body().getMain().getPressure());
                humidity.setText("Humidity :"+" "+response.body().getMain().getHumidity());
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

    private void saveInfo() {
        SharedPreferences sp=getSharedPreferences("city", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("city",bar.getText().toString());
        editor.apply();
        Toast.makeText(this,"done",Toast.LENGTH_SHORT).show();

    }
}