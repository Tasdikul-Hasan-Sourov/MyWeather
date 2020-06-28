package com.example.myweatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity implements CityAdapters.SelectedCity{
    RecyclerView recyclerView;
    List<CountryModel> countryModelList=new ArrayList<>();
    String[] names={"Bangladesh","India","Japan","Vietnam"};
    CityAdapters cityAdapters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView=(RecyclerView) findViewById(R.id.recView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        for(String s: names){
            CountryModel countryModel= new CountryModel(s);
            countryModelList.add(countryModel);
        }
        cityAdapters=new CityAdapters(countryModelList,this);
        recyclerView.setAdapter(cityAdapters);
    }

    @Override
    public void selectedCity(CountryModel countryModel) {
        Intent c=new Intent(getApplicationContext(),CityDetails.class);
        startActivity(c);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem=menu.findItem(R.id.search_view);
        SearchView searchView=(SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cityAdapters.getFilter().filter(newText);
                return true;
            }
        });
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.search_view){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}