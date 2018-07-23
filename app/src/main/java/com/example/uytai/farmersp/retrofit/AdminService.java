package com.example.uytai.farmersp.retrofit;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AdminService {
    @FormUrlEncoded
    @POST("deletend.php")
    Call<POST> deletend(@Field("id") int id);

    @FormUrlEncoded
    @POST("deletetl.php")
    Call<POST> deletetl(@Field("id") int id);
}
