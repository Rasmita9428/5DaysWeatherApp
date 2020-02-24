package com.example.dell.a5daysweatherapplication;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    GPSTracker gpsTracker;
    TextView txtCity, updated_time, txttemp, txtsunrise, txtsunset, txtwind, txtpressure, txthumidity, txtabout;
    String str_currentlatitude, str_currentlongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        checkPermission();
    }

    public void initialize() {
        txtCity = findViewById(R.id.txtCity);
        updated_time = findViewById(R.id.updated_time);
        txttemp = findViewById(R.id.txttemp);
        txtsunrise = findViewById(R.id.txtsunrise);
        txtsunset = findViewById(R.id.txtsunset);
        txtwind = findViewById(R.id.txtwind);
        txtpressure = findViewById(R.id.txtpressure);
        txthumidity = findViewById(R.id.txthumidity);
        txtabout = findViewById(R.id.txtabout);
    }
    public void checkPermission() {
        gpsTracker = new GPSTracker(MainActivity.this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION};
            if (isPermissionNotGranted(MainActivity.this, permissions)) {
                requestPermissions();
                return;
            } else {
                str_currentlatitude = String.valueOf(gpsTracker.getLatitude());
                str_currentlongitude = String.valueOf(gpsTracker.getLongitude());
                Toast.makeText(MainActivity.this, str_currentlatitude + str_currentlongitude, Toast.LENGTH_SHORT).show();
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
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                CommonKeys.PERMISSION_CODE
        );
    }

       @TargetApi(Build.VERSION_CODES.M)
       @RequiresApi(api = Build.VERSION_CODES.M)
       public static boolean isPermissionNotGranted(Context context, String[] permissions) {
           boolean flag = false;
           for (int i = 0; i < permissions.length; i++) {
               if (context.checkSelfPermission(permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                   flag = true;
                   break;
               }
           }
           return flag;
       }
    }

