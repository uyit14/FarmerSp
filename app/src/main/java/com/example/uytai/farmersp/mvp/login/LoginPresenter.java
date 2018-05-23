package com.example.uytai.farmersp.mvp.login;

import com.example.uytai.farmersp.model.UserModel;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.UserNDService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by uytai on 4/16/2018.
 */

public class LoginPresenter implements ILogin.Presenter {
    private final ILogin.View mLoginView;

    public LoginPresenter(ILogin.View mLoginView) {
        this.mLoginView = mLoginView;
    }

    @Override
    public void requestGetListUser() {
        UserNDService userNDService = ApiClient.getClient().create(UserNDService.class);
        Call<List<UserModel>> call = userNDService.getUserND();
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        mLoginView.getListUserSuccess(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                mLoginView.getListUserFail();
            }
        });
    }
}
