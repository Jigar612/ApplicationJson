package com.jigar.android.applicationjson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InsertRecord extends AppCompatActivity {

    Button btn_ok;
    EditText ed_cust_nm,ed_mobile_no,ed_tot_mem;
    Spinner spinner_table_no;
    ArrayAdapter<String>adapter_table_no;
    List<String>list_tab_no;
    String cust_nm=null,cust_mobile_no=null,tot_member=null,status_code,str_date,str_table_no;

    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_record);

        btn_ok = (Button) findViewById(R.id.btn_ok);
        ed_cust_nm= (EditText) findViewById(R.id.ed_cust_nm);
        ed_mobile_no= (EditText) findViewById(R.id.ed_mobile_no);
        ed_tot_mem= (EditText) findViewById(R.id.ed_tot_member);
        spinner_table_no= (Spinner) findViewById(R.id.spiner_tab_no);


        list_tab_no= new ArrayList<String>();
        list_tab_no.add("1");
        list_tab_no.add("2");
        list_tab_no.add("3");
        list_tab_no.add("4");


        adapter_table_no= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, list_tab_no);
        spinner_table_no.setAdapter(adapter_table_no);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
        str_date = df.format(c);


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_table_no= spinner_table_no.getSelectedItem().toString();
                cust_nm=  ed_cust_nm.getText().toString();
                cust_mobile_no= ed_mobile_no.getText().toString();
                tot_member=ed_tot_mem.getText().toString();
                status_code="1";


                if(isEmpty(cust_nm))
                {
                    ed_cust_nm.setError("Please insert customer name");
                    ed_cust_nm.requestFocus();
                }
                else if(isEmpty(cust_mobile_no))
                {
                    ed_mobile_no.setError("Please insert customer name");
                    ed_mobile_no.requestFocus();
                }
                else if(cust_mobile_no.length() < 10)
                {
                    ed_mobile_no.setError("Please enter minimum 10 digit");
                    ed_mobile_no.requestFocus();

                }
                else if(isEmpty(tot_member))
                {
                    ed_tot_mem.setError("Please insert customer name");
                    ed_tot_mem.requestFocus();
                }
                else
                {
                    new Insert_record().execute();
                }
            }
        });



    }
    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.
                INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return true;
    }

    class Insert_record extends AsyncTask<String, String, JSONObject> {
        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String insert_record = "http://192.185.129.71/~webservices1/resto/insertTabelBookingDetail.php";

        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(InsertRecord.this);
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {

                List<NameValuePair>list = new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("date",str_date ));
                list.add(new BasicNameValuePair("tabel_no", str_table_no));
                list.add(new BasicNameValuePair("customer_name",cust_nm));
                list.add(new BasicNameValuePair("mobile_number",cust_mobile_no));
                list.add(new BasicNameValuePair("tot_member",tot_member));
                list.add(new BasicNameValuePair("status",status_code));


                Log.d("request", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        insert_record, "POST", list);

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

            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (json != null) {
                try {
                    JSONObject json_obj = new JSONObject(json.toString());
                    String response = json_obj.getString("message");

                if(response.equals("success"))
                {
                    Toast.makeText(InsertRecord.this, "Record is insert", Toast.LENGTH_SHORT).show();
                    ed_cust_nm.setText("");
                    ed_mobile_no.setText("");
                    ed_tot_mem.setText("");
                }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }

    }
}
