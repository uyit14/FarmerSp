package com.example.uytai.farmersp.retrofit;

import com.example.uytai.farmersp.model.NongSan;

import java.io.File;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by uytai on 5/20/2018.
 */

public interface NongSanService {
    @FormUrlEncoded
    @POST("nongdan/getnongsanbyidnd.php")
    Call<List<NongSan>> getNongSanbyIDND(@Field("id_nongdan") int idnd);
}
