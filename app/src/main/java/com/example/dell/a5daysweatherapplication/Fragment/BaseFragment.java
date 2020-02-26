package com.example.dell.a5daysweatherapplication.Fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.dell.a5daysweatherapplication.MainActivity;


public class BaseFragment extends Fragment {

    public static MainActivity mActivity;
    public Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        this.mActivity = (MainActivity) getActivity();
    }

}
