package com.jigar.android.applicationjson

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.jigar.android.applicationjson.interfaces.LongEasyApi
import com.jigar.android.applicationjson.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class LoginKotlin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_kotlin)

        var ed_user_nm = findViewById<View>(R.id.ed_user_nm) as EditText
        var ed_pass = findViewById<View>(R.id.ed_pass) as EditText
        var spinner_login_with = findViewById<View>(R.id.spinner_login_with) as Spinner
        var btn_login = findViewById<View>(R.id.btn_login) as Button

        val spiner_items = ArrayList<String>()
        spiner_items.add("admin")
        spiner_items.add("waiter")

        var progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait")
        progressDialog.setCancelable(false)
        progressDialog.setIndeterminate(true)

        val arrayAdapter = ArrayAdapter(applicationContext, R.layout.support_simple_spinner_dropdown_item, spiner_items)
        spinner_login_with.adapter = arrayAdapter

        btn_login.setOnClickListener {
            progressDialog.show()
            val user_nm = ed_user_nm.text.toString().trim()
            val password = ed_pass.text.toString().trim()
            val type = spinner_login_with.selectedItem.toString()


            val retrofit = Retrofit.Builder()
                    .baseUrl("http://192.185.129.71/~webservices1/resto/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            val services = retrofit.create(LongEasyApi::class.java)
            val call = services.userLogin(user_nm, password, type)

            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val getRes = response.body().message
                    if (getRes == "admin") {
                        Toast.makeText(this@LoginKotlin, "Login Success" + getRes, Toast.LENGTH_SHORT).show()
                        val intent_activity2 = Intent(this@LoginKotlin, Main2Activity::class.java)
                        intent_activity2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent_activity2)
                    } else if (getRes == "waiter") {
                        Toast.makeText(this@LoginKotlin, "Login Success-" + getRes, Toast.LENGTH_SHORT).show()
                        val intent_activity2 = Intent(this@LoginKotlin, Main2Activity::class.java)
                        intent_activity2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent_activity2)
                    } else {
                        Toast.makeText(this@LoginKotlin, "Please check your credtial" + getRes, Toast.LENGTH_SHORT).show()
                    }
                    progressDialog.dismiss()

                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                }
            })
        }
    }
}
