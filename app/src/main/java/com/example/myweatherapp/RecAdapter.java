package com.example.myweatherapp;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
            holder.date.setText(item.getMain().getTemp());
            holder.temp.setText(item.getMain().getHumidity());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ApiViewHolder extends RecyclerView.ViewHolder {
        TextView date,temp;

        public ApiViewHolder(@NonNull View itemView) {
            super(itemView);
            date=(TextView) itemView.findViewById(R.id.dateFor);
            temp=(TextView) itemView.findViewById(R.id.tempFor);
        }
    }
}
