package com.example.dell.a5daysweatherapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import com.example.dell.a5daysweatherapplication.Adapter.TabAdapter;


public class MainActivity extends AppCompatActivity {
    GPSTracker gpsTracker;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    TabAdapter pageAdapter;
    TabItem tabtoday;
    TabItem tab5days;

    String City_ID;
    RelativeLayout rl_mainContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        // new weatherTask().execute();

    }

    public void initialize() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tablayout);
        tabtoday = findViewById(R.id.tabtoday);
        tab5days = findViewById(R.id.tab5days);
        viewPager = findViewById(R.id.viewPager);

        pageAdapter = new TabAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.colorAccent));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.colorAccent));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                                R.color.colorAccent));
                    }
                } else {
                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            android.R.color.darker_gray));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            android.R.color.darker_gray));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                                android.R.color.darker_gray));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }


//    class weatherTask extends AsyncTask<String, Void, String> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            /* Showing the ProgressBar, Making the main design GONE */
//            findViewById(R.id.loader).setVisibility(View.VISIBLE);
//            findViewById(R.id.rl_mainContainer).setVisibility(View.GONE);
//            findViewById(R.id.errorText).setVisibility(View.GONE);
//        }
//
//        protected String doInBackground(String... args) {
//            String response = HttpRequest.excuteGet("https://api.openweathermap.org/data/2.5/weather?lat="+str_currentlatitude+"&lon="+str_currentlongitude + "&units=metric&appid=" + API);
//            return response;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//
//            try {
//                JSONObject jsonObj = new JSONObject(result);
//                Log.e("daydata", String.valueOf(jsonObj));
//                JSONObject main = jsonObj.getJSONObject("main");
//                JSONObject sys = jsonObj.getJSONObject("sys");
//                JSONObject wind = jsonObj.getJSONObject("wind");
//                JSONObject weather = jsonObj.getJSONArray("weather").getJSONObject(0);
//                City_ID=weather.getString("id");
//                Log.e("City_id",City_ID);
//                Long updatedAt = jsonObj.getLong("dt");
//                String updatedAtText = "Updated at: " + new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(new Date(updatedAt * 1000));
//                String temp = main.getString("temp") + "°C";
////                String tempMin = "Min Temp: " + main.getString("temp_min") + "°C";
////                String tempMax = "Max Temp: " + main.getString("temp_max") + "°C";
//
//                Long sunrise = sys.getLong("sunrise");
//                Long sunset = sys.getLong("sunset");
//                String weatherDescription = weather.getString("description");
//
//                String address = jsonObj.getString("name") + ", " + sys.getString("country");
//
//
//                /* Populating extracted data into our views */
//                txtCity.setText(address);
//                updated_time.setText(updatedAtText);
//                txttemp.setText(temp);
//
//                txtsunrise.setText(new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date(sunrise * 1000)));
//                txtsunset.setText(new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date(sunset * 1000)));
//
//
//                /* Views populated, Hiding the loader, Showing the main design */
//                findViewById(R.id.loader).setVisibility(View.GONE);
//                findViewById(R.id.rl_mainContainer).setVisibility(View.VISIBLE);
//
//
//            } catch (JSONException e) {
//                findViewById(R.id.loader).setVisibility(View.GONE);
//                findViewById(R.id.errorText).setVisibility(View.VISIBLE);
//            }
//
//        }
//    }

}

