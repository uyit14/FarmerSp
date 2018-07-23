package com.example.uytai.farmersp.retrofit;

import com.example.uytai.farmersp.model.DangKyModel;
import com.example.uytai.farmersp.model.LoaiNSModel;
import com.example.uytai.farmersp.model.QuanHuyenModel;
import com.example.uytai.farmersp.model.ThuMuaModel;
import com.example.uytai.farmersp.model.NongSanModel;
import com.example.uytai.farmersp.model.ThuongLaiModel;
import com.example.uytai.farmersp.model.NongDanModel;
import com.example.uytai.farmersp.model.TinhThanhModel;

import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by uytai on 4/16/2018.
 */

public interface NongDanService {
    @GET("getuser.php")
    Call<List<NongDanModel>> getUserND();

    @FormUrlEncoded
    @POST("editprofile.php")
    Call<POST> editProfile(@Field("id") int idtt, @Field("ten") String ten, @Field("status") String status,
                           @Field("sdt") String sdt, @Field("avatar") String hinhanh);

    @FormUrlEncoded
    @POST("getnongsanbyidnd.php")
    Call<List<NongSanModel>> getNongSanbyIDND(@Field("id_nongdan") int idnd);

    @FormUrlEncoded
    @POST("getnongsanbyidnongsan.php")
    Call<List<NongSanModel>> getNongSanbyID(@Field("id") int id);

    @FormUrlEncoded
    @POST("getquanhuyenbyidtt.php")
    Call<List<QuanHuyenModel>> getQuanHuyen(@Field("id_tinhthanh") int idtt);

    @FormUrlEncoded
    @POST("deletenongsan.php")
    Call<POST> deletens(@Field("id") int id);

    @GET("getthuonglai.php")
    Call<List<ThuongLaiModel>> getTrader();

    @GET("getthumua.php")
    Call<List<ThuMuaModel>> getFeedThuMua();

    @GET("getloains.php")
    Call<List<LoaiNSModel>> getLoains();

    @GET("gettinhthanh.php")
    Call<List<TinhThanhModel>> getTinhThanh();

    @FormUrlEncoded
    @POST("signup.php")
    Call<List<NongDanModel>> signup(@Field("ten") String ten, @Field("avatar") String avatar
    , @Field("status") String status, @Field("sdt") String sdt,
                                    @Field("taikhoan") String taikhoan, @Field("matkhau") String matkhau);

    @FormUrlEncoded
    @POST("themnongsan.php")
    Call<List<NongSanModel>> themnongsan(@Field("tennongsan") String tennongsan, @Field("tg_batdau") String tg_batdau
            , @Field("tg_ketthuc") String tg_ketthuc, @Field("ten_lh") String ten_lh,
                                    @Field("sdt_lh") String sdt_lh, @Field("diachi") String diachi,
                                         @Field("hinhanh") String hinhanh, @Field("mota") String mota,
                                         @Field("id_nongdan") int id_nd, @Field("id_loains") int id_loains,
                                         @Field("id_quanhuyen") int id_quanhuyen, @Field("lat") double lat, @Field("lng") double lng);

    @FormUrlEncoded
    @POST("editnongsan.php")
    Call<POST> edittindadang(@Field("id") int id, @Field("tennongsan") String tennongsan, @Field("tg_batdau") String tg_batdau
            , @Field("tg_ketthuc") String tg_ketthuc, @Field("ten_lh") String ten_lh,
                                         @Field("sdt_lh") String sdt_lh, @Field("diachi") String diachi,@Field("hinhanh") String hinhanh,
                             @Field("mota") String mota, @Field("id_nongdan") int idnt, @Field("id_loains") int idloains, @Field("id_quanhuyen") int idqh);

    @FormUrlEncoded
    @POST("dangky.php")
    Call<POST> dangky(@Field("idnd") int idnd, @Field("idtl") int idtl);

    @GET("getdangky.php")
    Call<List<DangKyModel>> getDangKy();

    @FormUrlEncoded
    @POST("getidtlbyidnd.php")
    Call<List<DangKyModel>> getIDTL(@Field("idnd") int idnd);

    @FormUrlEncoded
    @POST("getthuonglaibyidtl.php")
    Call<List<ThuongLaiModel>> getThuongLaibyID(@Field("idtl[]") ArrayList<Integer> idtl);

//    @Multipart
//    @POST("getthuonglaibyidtl.php")
//    Call<List<ThuongLaiModel>> getThuongLaibyID(@Query("idtl[]") ArrayList<Integer> idtl);
}
