package com.example.rartamonov.translater.controller;

import okhttp3.OkHttpClient;

public class HttpClient extends OkHttpClient{

    private static HttpClient mInstance = null;

    private HttpClient() {
        OkHttpClient client = new OkHttpClient();
    }

    public static HttpClient getInstance(){

        if(mInstance==null){
            mInstance = new HttpClient();
        }

        return mInstance;
    }
}
