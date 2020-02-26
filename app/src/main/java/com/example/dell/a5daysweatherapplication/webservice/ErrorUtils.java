package com.example.dell.a5daysweatherapplication.webservice;


import android.content.Context;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtils {

    public static Context mActivity = null;

    public ErrorUtils(Context mActivity) {
        this.mActivity = mActivity;
    }

    public static APIError parseError(Response<?> response) {
        Converter<ResponseBody, APIError> converter = WebApiClient.getInstance(mActivity).retrofit_new
                .responseBodyConverter(APIError.class, new Annotation[0]);

        APIError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new APIError();
        }

        return error;
    }
}