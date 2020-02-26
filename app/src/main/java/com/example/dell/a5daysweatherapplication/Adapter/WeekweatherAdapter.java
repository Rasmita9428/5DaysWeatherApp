package com.example.dell.a5daysweatherapplication.Adapter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.a5daysweatherapplication.R;

import java.util.List;

public class WeekweatherAdapter extends RecyclerView.Adapter {
    List<com.example.dell.a5daysweatherapplication.model.weekweather.List> weekWeatherList;
    String City;

    public WeekweatherAdapter(List<com.example.dell.a5daysweatherapplication.model.weekweather.List> weekWeatherList, String City) {
        this.weekWeatherList = weekWeatherList;
        this.City = City;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v1 = inflater.inflate(R.layout.row_weekweather_adapter, parent, false);
        viewHolder = new WeekWeatherHolder(v1);

        return viewHolder;

    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("StaticFieldLeak")
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (weekWeatherList.get(position) != null) {

            final WeekWeatherHolder WeekWeatherHolder = (WeekWeatherHolder) holder;


            WeekWeatherHolder.txtCity.setText(City);
//            WeekWeatherHolder.weather.setText(response.body().getWeather().get(0).getDescription());
//            // updated_time.setText(response.body().get());
//            Long updatedAt = Long.valueOf(response.body().getDt());
//            WeekWeatherHolder.updated_time.setText("Updated at: " + new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(new Date(updatedAt * 1000)));
//            WeekWeatherHolder.txttemp.setText(response.body().getMain().getTemp().toString() + "°C");


        }

    }

    @Override
    public int getItemCount() {
        return weekWeatherList.size();
    }

    public void updateData(List<com.example.dell.a5daysweatherapplication.model.weekweather.List> notesLists) {
        this.weekWeatherList = notesLists;
        notifyDataSetChanged();
    }

    public class WeekWeatherHolder extends RecyclerView.ViewHolder {
        TextView txtCity, updated_time, txttemp, weather;


        public WeekWeatherHolder(View view) {
            super(view);
            txtCity = view.findViewById(R.id.txtCity);
            updated_time = view.findViewById(R.id.updated_time);
            txttemp = view.findViewById(R.id.txttemp);
            weather = view.findViewById(R.id.weather);
        }
    }
}

