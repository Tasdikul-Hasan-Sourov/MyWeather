package com.example.myweatherapp;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ApiViewHolder> {
    private Context context;
    private List<MyList> items;

    public RecAdapter(Context context, List<MyList> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ApiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.item,parent,false);
        return new ApiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApiViewHolder holder, int position) {
            MyList item=items.get(position);
            holder.temp.setText(item.getMain().getTemp()+"°C");
            holder.feels.setText(item.getMain().getFeelsLike()+"°C");
            holder.humidity.setText(item.getMain().getHumidity());
            holder.pressure.setText(item.getMain().getPressure());
            holder.state.setText(item.getList().get(0).getMainLine());
            holder.description.setText(item.getList().get(0).getDescription());
            holder.date.setText(item.getDate());
            Picasso.with(context)
                    .load(item.getList().get(0).getIcon())
                    .into(holder.img);



    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ApiViewHolder extends RecyclerView.ViewHolder {
        TextView temp,feels,humidity,pressure,state,description,date;
        ImageView img;

        public ApiViewHolder(@NonNull View itemView) {
            super(itemView);
            temp=(TextView) itemView.findViewById(R.id.tempFor);
            feels=(TextView) itemView.findViewById(R.id.feelsFor);
            humidity=(TextView) itemView.findViewById(R.id.humidityFor);
            pressure=(TextView) itemView.findViewById(R.id.pressureFor);
            state=(TextView) itemView.findViewById(R.id.stateFor);
            description=(TextView) itemView.findViewById(R.id.desFor);
            date=(TextView) itemView.findViewById(R.id.dateFor);
            img=(ImageView) itemView.findViewById(R.id.img);


        }
    }
}
