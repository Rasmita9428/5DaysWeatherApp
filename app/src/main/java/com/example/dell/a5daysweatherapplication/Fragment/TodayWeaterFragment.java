package com.example.dell.a5daysweatherapplication.Fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.a5daysweatherapplication.Comman_Method;
import com.example.dell.a5daysweatherapplication.CommonKeys;
import com.example.dell.a5daysweatherapplication.GPSTracker;
import com.example.dell.a5daysweatherapplication.R;
import com.example.dell.a5daysweatherapplication.model.Example;
import com.example.dell.a5daysweatherapplication.webservice.WebApiClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayWeaterFragment extends BaseFragment {

    String str_currentlatitude = "";
    String str_currentlongitude = "";
    String API = "02e24deaa9fa3286feaeead84040b350";
    TextView txtCity, updated_time, txttemp, weather;
    GPSTracker gpsTracker;

    public TodayWeaterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TodayWeather();
        return inflater.inflate(R.layout.fragment_today_weater, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtCity = view.findViewById(R.id.txtCity);
        updated_time = view.findViewById(R.id.updated_time);
        txttemp = view.findViewById(R.id.txttemp);
        weather = view.findViewById(R.id.weather);


        checkPermission();
        TodayWeather();
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
                CommonKeys.mActivity,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                CommonKeys.PERMISSION_CODE
        );
    }


    private void TodayWeather() {

//            final ProgressDialog progressDialog = new ProgressDialog(context);
//            progressDialog.setMessage("Please Wait...");
//            progressDialog.show();

        WebApiClient.getInstance(mContext).getWebApi().callweatherByLatLng(str_currentlatitude, str_currentlongitude, API, "metric").enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                if (response.code() == 200) {

                    txtCity.setText(response.body().getName());
                    weather.setText(response.body().getWeather().get(0).getDescription());
                    // updated_time.setText(response.body().get());
                    Long updatedAt = Long.valueOf(response.body().getDt());
                    updated_time.setText("Updated at: " + new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(new Date(updatedAt * 1000)));
                    txttemp.setText(response.body().getMain().getTemp().toString() + "°C");


                    Log.e("data", "onResponse: " + response.body().getCoord().getLat().toString());
                } else {

                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable throwable) {
            }
        });
    }


}
