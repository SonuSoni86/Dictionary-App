package com.example.retrofitexample2.RetrofitDao;

import com.example.retrofitexample2.Model.Content;
import com.example.retrofitexample2.Model.Meaning;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface JSonPlaceHolderApi {

    @GET("photos")
    Call<List<Content>> getAllPhotos();


    @Headers("X-Mashape-Key: c2b4fe5d00msh2cbc510d6d62e14p170844jsn0a0e97e3ff7e")
    @GET("words/{word}/definitions")
    Call<Meaning> getMeaning(@Path("word")String word);
}
