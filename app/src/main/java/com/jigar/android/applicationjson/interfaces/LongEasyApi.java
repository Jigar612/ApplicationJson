package com.jigar.android.applicationjson.interfaces;

import com.jigar.android.applicationjson.models.GetTableResponse;
import com.jigar.android.applicationjson.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by COMP11 on 21-Nov-18.
 */

public interface LongEasyApi {
    @FormUrlEncoded
    @POST("login.php")
   // Call<LoginResponse> getMessage(@Body Response response);
    Call<LoginResponse> userLogin(@Field("user_nm") String user_nm,
                                  @Field("pass") String pass,
                                  @Field("login_type") String user_type);


    @FormUrlEncoded
    @POST("viewInsertedTabelRecordList.php")
        // Call<LoginResponse> getMessage(@Body Response response);
    Call<GetTableResponse> getTableRecord(@Field("tabel_no") String table_no,
                                          @Field("status") String status);
}
