package com.jigar.android.applicationjson;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   // ProgressDialog pd;
    String ProducList_Url="http://192.185.129.71/~webservices1/resto/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new checkLogin().execute();
      //  new checkLogin().execute(null,null);
    }
    class checkLogin extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pd;

        //private static final String LOGIN_URL = "my_url";

        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Attempting login...");
            pd.setIndeterminate(false);
            pd.setCancelable(true);
            pd.show();
        }

        protected JSONObject doInBackground(String... params) {

            try {
                List<NameValuePair>list = new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("user_nm","jigar"));
                list.add(new BasicNameValuePair("pass","123"));
                list.add(new BasicNameValuePair("login_type","admin"));
                //Log.d("request", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        ProducList_Url, "POST", list);

                if (json != null) {
                    Log.d("JSON result", json.toString());

                    return json;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(JSONObject json) {

            int success = 0;
            String message = "";

            if (pd != null && pd.isShowing()) {
                pd.dismiss();
            }

            if (json != null) {

                if(json.equals("admin"))
                {
//                    Toast.makeText(MainActivity.this, json.toString(),
//                            Toast.LENGTH_LONG).show();
                    Intent intent_insertRecord= new Intent(MainActivity.this,InsertRecord.class);
                    intent_insertRecord.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent_insertRecord);
                }
                else
                {
                    Intent intent_insertRecord= new Intent(MainActivity.this,InsertRecord.class);
                    intent_insertRecord.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent_insertRecord);
                }

//                try {
//                    success = json.getInt(TAG_SUCCESS);
//                    message = json.getString(TAG_MESSAGE);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }

            if (success == 1) {
                Log.d("Success!", message);
            } else {
                Log.d("Failure", message);
            }
        }
    }


}
