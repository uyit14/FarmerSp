package com.example.uytai.farmersp.mvp.register;

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
 * Created by uytai on 5/24/2018.
 */

public class RegisterPresenter implements IRegister.Presenter {
    private final IRegister.View mRegisterView;

    public RegisterPresenter(IRegister.View mRegisterView) {
        this.mRegisterView = mRegisterView;
    }

    @Override
    public void requestSignUp() {
        mRegisterView.showDialogProgress();
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<List<NongDanModel>> call = nongDanService.signup(RegisterActivity.ten,RegisterActivity.avatar, RegisterActivity.status,
                RegisterActivity.sdt, RegisterActivity.taikhoan, RegisterActivity.matkhau );
        call.enqueue(new Callback<List<NongDanModel>>() {
            @Override
            public void onResponse(Call<List<NongDanModel>> call, Response<List<NongDanModel>> response) {
                mRegisterView.SignUpSuccess();
                mRegisterView.dismissDialog();
            }

            @Override
            public void onFailure(Call<List<NongDanModel>> call, Throwable t) {
                mRegisterView.SignUpFail();
                mRegisterView.dismissDialog();
            }
        });
    }

    @Override
    public void requestSignUpThuonglai() {
        mRegisterView.showDialogProgress();
        ThuonglaiService thuonglaiService = ApiClient.getClient().create(ThuonglaiService.class);
        Call<List<ThuongLaiModel>> call = thuonglaiService.signupTL(RegisterActivity.ten,RegisterActivity.avatar,
                RegisterActivity.rate, RegisterActivity.taikhoan, RegisterActivity.matkhau);
        call.enqueue(new Callback<List<ThuongLaiModel>>() {
            @Override
            public void onResponse(Call<List<ThuongLaiModel>> call, Response<List<ThuongLaiModel>> response) {
                mRegisterView.SignUpSuccess();
                mRegisterView.dismissDialog();
            }

            @Override
            public void onFailure(Call<List<ThuongLaiModel>> call, Throwable t) {
                mRegisterView.SignUpFail();
                mRegisterView.dismissDialog();
            }
        });
    }

    @Override
    public void requestGetNongDan() {
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<List<NongDanModel>> call = nongDanService.getUserND();
        call.enqueue(new Callback<List<NongDanModel>>() {
            @Override
            public void onResponse(Call<List<NongDanModel>> call, Response<List<NongDanModel>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        mRegisterView.getLisNongDanSuccess(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<NongDanModel>> call, Throwable t) {

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
                        mRegisterView.getListThuongLaiSuccess(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ThuongLaiModel>> call, Throwable t) {

            }
        });
    }
}
