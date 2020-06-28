package com.example.myweatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CityAdapters extends RecyclerView.Adapter<CityAdapters.CityAdaptervh> implements Filterable {

    private List<CountryModel> countryModelList;
    private  List<CountryModel> getCountryModelListFiltered;
    private Context context;
    private SelectedCity selectedCity;

    public CityAdapters(List<CountryModel> countryModelList, SelectedCity selectedCity) {
        this.countryModelList = countryModelList;
        this.getCountryModelListFiltered=countryModelList;
        this.selectedCity=selectedCity;
    }

    @NonNull
    @Override
    public CityAdapters.CityAdaptervh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new CityAdaptervh(LayoutInflater.from(context).inflate(R.layout.item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapters.CityAdaptervh holder, int position) {
         CountryModel countryModel=countryModelList.get(position);
         String cityname= countryModel.getCountry();
         String prefix= countryModel.getCountry().substring(0,1);
         holder.cityname.setText(cityname);
         holder.prefix.setText(prefix);
    }

    @Override
    public int getItemCount() {
        return countryModelList.size();
    }

    @Override
    public Filter getFilter() {
       Filter filter=new Filter() {
           @Override
           protected FilterResults performFiltering(CharSequence charSequence) {
               FilterResults filterResults=new FilterResults();
               if(charSequence==null | charSequence.length()==0){
                   filterResults.count=getCountryModelListFiltered.size();
                   filterResults.values=getCountryModelListFiltered;
               }
               else{
                   String searchChr=charSequence.toString().toLowerCase();
                   List<CountryModel> resultData=new ArrayList<>();
                   for(CountryModel countryModel:getCountryModelListFiltered){
                       if (countryModel.getCountry().toLowerCase().contains(searchChr)){
                           resultData.add(countryModel);
                       }
                       filterResults.count=resultData.size();
                       filterResults.values=resultData;
                   }
               }
               return filterResults;
           }

           @Override
           protected void publishResults(CharSequence constraint, FilterResults filterresults) {
            countryModelList= (List<CountryModel>) filterresults.values;
            notifyDataSetChanged();
           }
       };
        return filter;
    }


    public interface SelectedCity{
        void selectedCity(CountryModel countryModel);
    }
    public  class  CityAdaptervh extends RecyclerView.ViewHolder {
        TextView prefix,cityname;
        Button click;
        public CityAdaptervh(@NonNull View itemView) {
            super(itemView);
            prefix=itemView.findViewById(R.id.prefix);
            cityname=itemView.findViewById(R.id.cityName);
            click=itemView.findViewById(R.id.click);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedCity.selectedCity(countryModelList.get(getAdapterPosition()));
                }
            });
        }
    }
}
