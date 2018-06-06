package com.example.uytai.farmersp.retrofit;

import com.example.uytai.farmersp.model.NongDanModel;
import com.example.uytai.farmersp.model.ThuMuaModelTL;
import com.example.uytai.farmersp.model.ThuongLaiModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ThuonglaiService {

    @FormUrlEncoded
    @POST("nongdan/getuser.php")
    Call<List<ThuMuaModelTL>> getTinDaDangByIDTL(@Field("id") int idtl);

    @FormUrlEncoded
    @POST("nongdan/signup.php")
    Call<List<ThuongLaiModel>> signupTL(@Field("ten") String ten, @Field("avatar") String avatar, @Field("sdt") String sdt,
                                    @Field("taikhoan") String taikhoan, @Field("matkhau") String matkhau);

    @GET("nongdan/getuser.php")
    Call<List<ThuongLaiModel>> getUserTL();
}
