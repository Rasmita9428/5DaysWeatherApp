package com.example.dell.a5daysweatherapplication.webservice;

/**
 * Created by mind on 2/7/16.
 */

public class APIError {

    private int statusCode;
    private String message;

    public APIError() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }
}
