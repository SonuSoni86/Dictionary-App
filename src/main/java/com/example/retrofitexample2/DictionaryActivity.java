package com.example.retrofitexample2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitexample2.Model.Meaning;
import com.example.retrofitexample2.RetrofitDao.JSonPlaceHolderApi;
import com.example.retrofitexample2.RetrofitDao.RetrofitClientInstance;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DictionaryActivity extends AppCompatActivity {

    private TextView meaning;
    private TextInputEditText eWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        meaning = findViewById(R.id.meaning);
        eWord= findViewById(R.id.word);
    }

    public void showClicked(View view) {

        if(TextUtils.isEmpty(eWord.getText())){
            eWord.setError("Enter a word");
            return;
        }
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();

        JSonPlaceHolderApi api = RetrofitClientInstance.getRetrofitInstance2().create(JSonPlaceHolderApi.class);
        final Call<Meaning> meaningCall = api.getMeaning(eWord.getText().toString().trim());

        meaningCall.enqueue(new Callback<Meaning>() {
            @Override
            public void onResponse(Call<Meaning> call, Response<Meaning> response) {
                progressDialog.dismiss();
                if(!response.isSuccessful()){
                   Toast.makeText(getApplicationContext(),"something went wrong: "+response.code(),Toast.LENGTH_LONG).show();
                    return;
                }
                String value = response.body().getDefinitions().get(0).getDefinition();
                meaning.setText(value);
            }

            @Override
            public void onFailure(Call<Meaning> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
