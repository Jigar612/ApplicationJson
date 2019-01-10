package com.jigar.android.applicationjson;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jigar.android.applicationjson.interfaces.LongEasyApi;
import com.jigar.android.applicationjson.models.LoginResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText ed_user_nm,ed_pass;
    Button btn_login;
    Spinner spinner_login_with;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_user_nm= (EditText) findViewById(R.id.ed_user_nm);
        ed_pass= (EditText) findViewById(R.id.ed_pass);
        spinner_login_with =(Spinner)findViewById(R.id.spinner_login_with);
        btn_login= (Button) findViewById(R.id.btn_login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);


        ArrayList<String> spiner_items = new ArrayList<>();
        spiner_items.add("admin");
        spiner_items.add("waiter");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,spiner_items);
        spinner_login_with.setAdapter(arrayAdapter);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_nm = ed_user_nm.getText().toString().trim();
                String password = ed_pass.getText().toString().trim();
                String type = spinner_login_with.getSelectedItem().toString();
                progressDialog.show();

                Retrofit retrofit =new Retrofit.Builder()
                        .baseUrl("http://192.185.129.71/~webservices1/resto/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                LongEasyApi services = retrofit.create(LongEasyApi.class);
                Call<LoginResponse> call = services.userLogin(user_nm,password,type);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        String getRes = response.body().getMessage();
                        if(getRes.equals("admin")) {
                            Toast.makeText(LoginActivity.this, "Login Success"+getRes, Toast.LENGTH_SHORT).show();
                            Intent intent_activity2 = new Intent(LoginActivity.this, Main2Activity.class);
                            intent_activity2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent_activity2);
                        }
                        else if(getRes.equals("waiter"))
                        {
                            Toast.makeText(LoginActivity.this, "Login Success-"+getRes, Toast.LENGTH_SHORT).show();
                            Intent intent_activity2 = new Intent(LoginActivity.this, Main2Activity.class);
                            intent_activity2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent_activity2);
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Please check your credtial"+getRes, Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        progressDialog.dismiss();
                    }
                });
            }
        });



    }
}
