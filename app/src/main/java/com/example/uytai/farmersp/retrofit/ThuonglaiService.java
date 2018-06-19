package com.example.uytai.farmersp.retrofit;

import com.example.uytai.farmersp.model.NongDanModel;
import com.example.uytai.farmersp.model.NongSanModelTL;
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
    @POST("nongdan/signuptl.php")
    Call<List<ThuongLaiModel>> signupTL(@Field("ten") String ten, @Field("avatar") String avatar, @Field("rate") double rate,
                                    @Field("taikhoan") String taikhoan, @Field("matkhau") String matkhau);

    @GET("nongdan/getthuonglai.php")
    Call<List<ThuongLaiModel>> getUserTL();

    @FormUrlEncoded
    @POST("nongdan/getthumuabyidtl.php")
    Call<List<ThuMuaModelTL>> getThuMuabyIDTL(@Field("id_thuonglai") int idtl);

    @FormUrlEncoded
    @POST("nongdan/getthumuabyidthumua.php")
    Call<List<ThuMuaModelTL>> getThuMuabyIDThuMua(@Field("id") int id);

    @FormUrlEncoded
    @POST("nongdan/themthumua.php")
    Call<POST> themthumua(@Field("ten_nongsan") String ten_nongsan, @Field("tg_batdau") String tg_batdau
            , @Field("tg_ketthuc") String tg_ketthuc, @Field("gia_thapnhat") double gia_thapnhat,
                                         @Field("gia_caonhat") double gia_caonhat, @Field("noithumua") String noithumua,
                                         @Field("lienhe") String lienhe, @Field("hinhanh") String hinhanh,
                                         @Field("mota") String mota, @Field("id_thuonglai") int id_thuonglai,
                                         @Field("id_loains") int id_loains );
    @FormUrlEncoded
    @POST("nongdan/editthumua.php")
    Call<POST> editthumua(@Field("id") int id, @Field("ten_nongsan") String ten_nongsan, @Field("tg_batdau") String tg_batdau
            , @Field("tg_ketthuc") String tg_ketthuc, @Field("gia_thapnhat") double gia_thapnhat,
                          @Field("gia_caonhat") double gia_caonhat, @Field("noithumua") String noithumua,
                          @Field("lienhe") String lienhe, @Field("hinhanh") String hinhanh,
                          @Field("mota") String mota, @Field("id_thuonglai") int id_thuonglai,
                          @Field("id_loains") int id_loains );

    @GET("nongdan/getnongsan.php")
    Call<List<NongSanModelTL>> getNongSanForTL();

    @FormUrlEncoded
    @POST("nongdan/getlistnsbyidloains.php")
    Call<List<NongSanModelTL>> getNongSanbyIDLNSForTL(@Field("id_loains") int id_loains);

    @FormUrlEncoded
    @POST("nongdan/editprofiletl.php")
    Call<POST> editProfileTL(@Field("id") int idtt, @Field("ten") String ten, @Field("status") String status
            , @Field("avatar") String avatar);
}
