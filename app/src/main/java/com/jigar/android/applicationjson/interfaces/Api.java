package com.jigar.android.applicationjson.interfaces;

import com.jigar.android.applicationjson.models.LoginGetResponse;
import com.jigar.android.applicationjson.models.LoginResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by COMP11 on 05-Dec-18.
 */

public interface Api {


    @GET("GetbyEmailPassword/{user_nm}/{pass}/{token_id}/{mobile_key}")
    Call<LoginGetResponse> userLogin(@Path("user_nm") String user_nm,
                                     @Path("pass") String pass,
                                     @Path("token_id")String token_id,
                                     @Path("mobile_key") String mobile_key);
}