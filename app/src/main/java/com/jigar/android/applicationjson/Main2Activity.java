package com.jigar.android.applicationjson;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.jigar.android.applicationjson.adapter.Adapter_recycler_tableRecord;
import com.jigar.android.applicationjson.adapter.Adapter_tableRecord;
import com.jigar.android.applicationjson.interfaces.LongEasyApi;
import com.jigar.android.applicationjson.models.GetTableResponse;
import com.jigar.android.applicationjson.models.LoginResponse;
import com.jigar.android.applicationjson.models.RecyclerAdapter;
import com.jigar.android.applicationjson.models.TableData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Main2Activity extends AppCompatActivity {
    Retrofit retrofit;
  //  ListView listView;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

       // listView= (ListView) findViewById(R.id.listview_display_table);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        int internet = ContextCompat.checkSelfPermission(Main2Activity.this, android.Manifest.permission.INTERNET);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (internet != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.INTERNET);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(Main2Activity.this, listPermissionsNeeded.toArray
                    (new String[listPermissionsNeeded.size()]), 1);

        }

          retrofit = new Retrofit.Builder()
                .baseUrl("http://192.185.129.71/~webservices1/resto/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final LongEasyApi services = retrofit.create(LongEasyApi.class);
        Call<GetTableResponse> getTableResponseCall= services.getTableRecord("1","1");

        getTableResponseCall.enqueue(new Callback<GetTableResponse>() {
            @Override
            public void onResponse(Call<GetTableResponse> call, Response<GetTableResponse> response) {

                List<TableData> tableRecord = response.body().getTableRecord();

                //Adapter_tableRecord adapter_tableRecord = new Adapter_tableRecord(tableRecord,Main2Activity.this,getLayoutInflater());
                //listView.setAdapter(adapter_tableRecord);
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(tableRecord,Main2Activity.this);
                recyclerView.setAdapter(recyclerAdapter); // set the Adapter to RecyclerView

//              Adapter_recycler_tableRecord adapter_recycler_tableRecord = new Adapter_recycler_tableRecord (tableRecord,Main2Activity.this);
//              recyclerView.setAdapter(adapter_recycler_tableRecord);
            }
            @Override
            public void onFailure(Call<GetTableResponse> call, Throwable t) {

            }
        });
    }
}
