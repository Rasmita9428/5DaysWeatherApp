package com.example.dell.a5daysweatherapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.a5daysweatherapplication.R;
import com.example.dell.a5daysweatherapplication.model.weekweather.MyList;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeekweatherAdapter extends RecyclerView.Adapter {
    MyList[] weekWeatherMyList;
    String City;
    Context context;
    public WeekweatherAdapter(MyList[] weekWeatherMyList, String City, Context mContext) {
        this.weekWeatherMyList = weekWeatherMyList;
        this.City = City;
        this.context=mContext;
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

        if (weekWeatherMyList != null) {

            final WeekWeatherHolder WeekWeatherHolder = (WeekWeatherHolder) holder;
            Picasso.with(context).load(new StringBuffer("https://openweathermap.org/img/w/").append(weekWeatherMyList[position].getWeather().get(0).getIcon()).append(".png").toString()).into(((WeekWeatherHolder) holder).image);
            Long updatedAt = Long.valueOf(weekWeatherMyList[position].getDt());
            WeekWeatherHolder.updated_time.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(new Date(updatedAt * 1000)));
            String tempincelsius= String.valueOf(((weekWeatherMyList[position].getMain().getTemp())));
            WeekWeatherHolder.txttemp.setText(tempincelsius + "°C");
            WeekWeatherHolder.weather.setText(weekWeatherMyList[position].getWeather().get(0).getDescription());
//            // updated_time.setText(response.body().get());
//            WeekWeatherHolder.txttemp.setText(weekWeatherMyList.get(position).getMain().getTemp().toString() + "°C");


        }

    }

    @Override
    public int getItemCount() {
        return weekWeatherMyList.length;
    }

    public void updateData(MyList[] notesMyLists) {
        this.weekWeatherMyList = notesMyLists;
        notifyDataSetChanged();
    }

    public class WeekWeatherHolder extends RecyclerView.ViewHolder {
        TextView txtdescription, updated_time, txttemp, weather;
        ImageView image;

        public WeekWeatherHolder(View view) {
            super(view);
            updated_time = view.findViewById(R.id.updated_time);
            txttemp = view.findViewById(R.id.txttemp);
            weather = view.findViewById(R.id.weather);
            image=view.findViewById(R.id.image);
        }
    }
}

