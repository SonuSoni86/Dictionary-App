package com.example.retrofitexample2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.retrofitexample2.Model.Content;
import com.example.retrofitexample2.RetrofitDao.JSonPlaceHolderApi;
import com.example.retrofitexample2.RetrofitDao.RetrofitClientInstance;
import com.example.retrofitexample2.View.PhotosAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private PhotosAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       progressDialog = new ProgressDialog(this);
       progressDialog.setMessage("Loading");
        progressDialog.show();
        JSonPlaceHolderApi placeHolderApi = RetrofitClientInstance.getRetrofitInstance().create(JSonPlaceHolderApi.class);
        recyclerView = findViewById(R.id.recycler_view);

        Call<List<Content>> listCall = placeHolderApi.getAllPhotos();

        listCall.enqueue(new Callback<List<Content>>() {
            @Override
            public void onResponse(Call<List<Content>> call, Response<List<Content>> response) {
                progressDialog.dismiss();
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),response.code(),Toast.LENGTH_LONG).show();
                    return;
                }
                generatePhotoList(response.body());
            }

            @Override
            public void onFailure(Call<List<Content>> call, Throwable t) {
               progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    private void generatePhotoList(List<Content> photoList){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PhotosAdapter(photoList,MainActivity.this);
        recyclerView.setAdapter(adapter);
    }

    public void btnClicked(View view) {
        startActivity(new Intent(MainActivity.this,DictionaryActivity.class));
    }
}
