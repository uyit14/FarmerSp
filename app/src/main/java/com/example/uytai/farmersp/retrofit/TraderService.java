package com.example.uytai.farmersp.retrofit;

import com.example.uytai.farmersp.model.TraderModel;
import com.example.uytai.farmersp.respone.TraderRespone;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

/**
 * Created by uytai on 4/13/2018.
 */

public interface TraderService {
    @GET("nongdan/getthuonglai.php")
    Call<List<TraderModel>> getTrader();
}
