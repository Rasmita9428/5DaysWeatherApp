package com.example.dell.a5daysweatherapplication.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dell.a5daysweatherapplication.Fragment.TodayWeaterFragment;
import com.example.dell.a5daysweatherapplication.Fragment.WeekweatherFragment;

public class TabAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public TabAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TodayWeaterFragment();
            case 1:
                return new WeekweatherFragment();
            case 2:
                return new TodayWeaterFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
