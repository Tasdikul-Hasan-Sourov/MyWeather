package com.example.myweatherapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button addButton,forcast;
    TextView temp,pressure,hum,topText,maxt,mint,mainl,descripmain;
    ImageView imgmain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);
        addButton = (Button) findViewById(R.id.addButton);
        temp = (TextView) findViewById(R.id.tempMain);
        pressure = (TextView) findViewById(R.id.desMain);
        hum = (TextView) findViewById(R.id.humidityMain);
        maxt=(TextView) findViewById(R.id.maxtMain);
        mint=(TextView) findViewById(R.id.mintMain);
        mainl=(TextView) findViewById(R.id.mainlMain);
        descripmain=(TextView) findViewById(R.id.descripMain);
        topText=(TextView) findViewById(R.id.topText);
        forcast=(Button) findViewById(R.id.foreCast);
        displayData();
        getCity(topText.getText().toString().trim());
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Search.class);
                startActivity(intent);
            }
        });

        forcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Forcast.class);
                startActivity(intent);
            }
        });
        }

    private void getCity(String name) {
        CityApi apiInterface= ApiClient.getClient().create(CityApi.class);
        Call<Example> call=  apiInterface.getCity(name);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                temp.setText(response.body().getMain().getTemp()+"°C");
               pressure.setText(response.body().getMain().getPressure()+"hp");
                hum.setText(response.body().getMain().getHumidity()+"%");
                maxt.setText(response.body().getMain().getTemp_max()+"°C");
                mint.setText(response.body().getMain().getTemp_min()+"°C");
                mainl.setText(response.body().getList().get(0).getMainLine());
                descripmain.setText(response.body().getList().get(0).getDescription());

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

    private void displayData() {
        SharedPreferences sp= getSharedPreferences("city", Context.MODE_PRIVATE);
        String city=sp.getString("city","").toUpperCase();
        topText.setText(city);
    }
}