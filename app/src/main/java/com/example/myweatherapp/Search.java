package com.example.myweatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    TextView temp,weather,min,max;
    EditText bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        bearch=(Button) findViewById(R.id.button);
        temp=(TextView) findViewById(R.id.temp);
        weather =(TextView) findViewById(R.id.weather);
        min =(TextView) findViewById(R.id.searchMin);
        max=(TextView) findViewById(R.id.searchMax);
        bar=(EditText) findViewById(R.id.search);
        add=(Button) findViewById(R.id.addMain);

        bearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckValidity();
                getCity(bar.getText().toString().trim());
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent m = new Intent(getApplicationContext(), MainActivity.class);
                    saveInfo();
                    startActivity(m);
                    finish();



            }
        });

    }

    private void CheckValidity() {
        String search=bar.getText().toString().trim();
        if(search.isEmpty())
        {
            bar.setError("Enter a city");
            bar.requestFocus();
            return;
        }


    }

    private void  getCity(String name){
        CityApi apiInterface= ApiClient.getClient().create(CityApi.class);
        Call<Example> call=  apiInterface.getCity(name);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(!response.isSuccessful()){
                    return;
                }
                temp.setText(response.body().getMain().getTemp()+"°C");
                weather.setText(response.body().getList().get(0).getMainLine());
                min.setText(response.body().getMain().getTemp_min()+"°C");
                max.setText(response.body().getMain().getTemp_max()+"°C");
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

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}