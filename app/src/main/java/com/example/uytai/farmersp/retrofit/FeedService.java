package com.example.uytai.farmersp.retrofit;

import com.example.uytai.farmersp.model.FeedModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by uytai on 4/15/2018.
 */

public interface FeedService {
    @GET("nongdan/getthumua.php")
    Call<List<FeedModel>> getFeedThuMua();
}
