package com.example.uytai.farmersp.retrofit;

import com.example.uytai.farmersp.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by uytai on 4/16/2018.
 */

public interface UserNDService {
    @GET("nongdan/getuser.php")
    Call<List<UserModel>> getUserND();
}
