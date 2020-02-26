package com.example.dell.a5daysweatherapplication.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.a5daysweatherapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeekweatherFragment extends Fragment {


    public WeekweatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weekweather, container, false);
    }

}
