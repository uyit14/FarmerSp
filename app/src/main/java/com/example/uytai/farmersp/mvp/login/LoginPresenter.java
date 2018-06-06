package com.example.uytai.farmersp.mvp.login;

import com.example.uytai.farmersp.model.NongDanModel;
import com.example.uytai.farmersp.model.ThuongLaiModel;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.NongDanService;
import com.example.uytai.farmersp.retrofit.ThuonglaiService;

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
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<List<NongDanModel>> call = nongDanService.getUserND();
        call.enqueue(new Callback<List<NongDanModel>>() {
            @Override
            public void onResponse(Call<List<NongDanModel>> call, Response<List<NongDanModel>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        mLoginView.getListUserSuccess(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<NongDanModel>> call, Throwable t) {
                mLoginView.getListUserFail();
            }
        });
    }

    @Override
    public void requestGetThuongLai() {
        ThuonglaiService thuonglaiService = ApiClient.getClient().create(ThuonglaiService.class);
        Call<List<ThuongLaiModel>> call = thuonglaiService.getUserTL();
        call.enqueue(new Callback<List<ThuongLaiModel>>() {
            @Override
            public void onResponse(Call<List<ThuongLaiModel>> call, Response<List<ThuongLaiModel>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        mLoginView.getListTLSuccess(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ThuongLaiModel>> call, Throwable t) {
                mLoginView.getListTLFail();
            }
        });
    }
}
