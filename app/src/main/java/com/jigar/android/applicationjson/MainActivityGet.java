package com.jigar.android.applicationjson;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jigar.android.applicationjson.interfaces.Api;
import com.jigar.android.applicationjson.interfaces.LongEasyApi;
import com.jigar.android.applicationjson.models.LoginGetResponse;
import com.jigar.android.applicationjson.models.LoginResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by COMP11 on 05-Dec-18.
 */

public class MainActivityGet extends AppCompatActivity {
    EditText ed_user_nm,ed_pass;
    Button btn_login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_user_nm= (EditText) findViewById(R.id.ed_user_nm);
        ed_pass= (EditText) findViewById(R.id.ed_pass);
        btn_login= (Button) findViewById(R.id.btn_login);

        int internet = ContextCompat.checkSelfPermission(MainActivityGet.this, android.Manifest.permission.INTERNET);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (internet != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.INTERNET);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(MainActivityGet.this, listPermissionsNeeded.toArray
                    (new String[listPermissionsNeeded.size()]), 1);

        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_nm = ed_user_nm.getText().toString().trim();
                String password = ed_pass.getText().toString().trim();

                Retrofit retrofit =new Retrofit.Builder()
                        .baseUrl("http://35.162.89.140:82/GotHireServices.svc/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();



                Api api = retrofit.create(Api.class);
               // Call<LoginGetResponse> call = api.getMessage();
                Call<LoginGetResponse> call = api.userLogin(user_nm,password,"123","1");

               call.enqueue(new Callback<LoginGetResponse>() {
                   @Override
                   public void onResponse(Call<LoginGetResponse> call, Response<LoginGetResponse> response) {
                       String getRes = response.body().getGetbyEmailPasswordResult();

                   }

                   @Override
                   public void onFailure(Call<LoginGetResponse> call, Throwable t) {

                   }
               });
            }
        });

    }
}
