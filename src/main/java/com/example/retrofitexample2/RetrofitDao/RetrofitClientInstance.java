package com.example.retrofitexample2.RetrofitDao;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance  {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String BASE_URL2 = "https://wordsapiv1.p.mashape.com/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
        if(retrofit==null){
            synchronized (RetrofitClientInstance.class){
                retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            }
        }
        return retrofit;
    }

    public static Retrofit getRetrofitInstance2(){
        if(retrofit==null){
            synchronized (RetrofitClientInstance.class){
                retrofit = new Retrofit.Builder().baseUrl(BASE_URL2).addConverterFactory(GsonConverterFactory.create()).build();
            }
        }
        return retrofit;
    }

}
