package com.example.dell.a5daysweatherapplication.Fragment;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.a5daysweatherapplication.Adapter.WeekweatherAdapter;
import com.example.dell.a5daysweatherapplication.Comman_Method;
import com.example.dell.a5daysweatherapplication.CommonKeys;
import com.example.dell.a5daysweatherapplication.GPSTracker;
import com.example.dell.a5daysweatherapplication.R;
import com.example.dell.a5daysweatherapplication.model.weekweather.MyList;
import com.example.dell.a5daysweatherapplication.model.weekweather.WeekWeather;
import com.example.dell.a5daysweatherapplication.webservice.WebApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeekweatherFragment extends BaseFragment {
    RecyclerView recycler_weektemp_list;
    String str_currentlatitude = "";
    String str_currentlongitude = "";
    WeekweatherAdapter adapter;
    String City;
    MyList[] weekWeatherMyList ;
    String API = "02e24deaa9fa3286feaeead84040b350";
    TextView txtCity, updated_time, txttemp, weather;
    GPSTracker gpsTracker;
    public WeekweatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_weekweather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtCity=view.findViewById(R.id.txt_city);
        recycler_weektemp_list = view.findViewById(R.id.recycler_weektemp_list);
        recycler_weektemp_list.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        recycler_weektemp_list.setLayoutManager(mLayoutManager);
        recycler_weektemp_list.setItemAnimator(new DefaultItemAnimator());

        checkPermission();
        WeekWeather();
    }

    public void checkPermission() {
        gpsTracker = new GPSTracker(mContext);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION};
            if (Comman_Method.isPermissionNotGranted(mContext, permissions)) {
                requestPermissions();
                return;
            } else {
                str_currentlatitude = String.valueOf(gpsTracker.getLatitude());
                str_currentlongitude = String.valueOf(gpsTracker.getLongitude());
                Toast.makeText(mContext, str_currentlatitude + str_currentlongitude, Toast.LENGTH_SHORT).show();
                Log.e("current", str_currentlatitude);
            }
        } else
            str_currentlatitude = String.valueOf(gpsTracker.getLatitude());
        str_currentlongitude = String.valueOf(gpsTracker.getLongitude());
        Log.e("Latlong", str_currentlatitude + str_currentlongitude);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CommonKeys.PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Granted. Start getting the location information
            }
        }
    }


    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                mActivity,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                CommonKeys.PERMISSION_CODE
        );
    }

    private void WeekWeather() {

        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        WebApiClient.getInstance(mContext).getWebApi().callWeekweatherByLatLng(str_currentlatitude, str_currentlongitude, API,"metric").enqueue(new Callback<WeekWeather>() {
            @Override
            public void onResponse(Call<WeekWeather> call, Response<WeekWeather> response) {
                progressDialog.dismiss();
                if (response.code() == 200) {
                    txtCity.setText("5 Days Weather of "+response.body().getCity().getName());
                    weekWeatherMyList = response.body().getList();
                    Log.e("WeekWeather", weekWeatherMyList.toString());
                    //Recycerview Featured Store
                    adapter = new WeekweatherAdapter(weekWeatherMyList, City,mContext);
                    recycler_weektemp_list.setAdapter(adapter);
                    recycler_weektemp_list.setNestedScrollingEnabled(false);
                } else {

                }
            }

            @Override
            public void onFailure(Call<WeekWeather> call, Throwable throwable) {
                progressDialog.dismiss();
            }
        });
    }

}
