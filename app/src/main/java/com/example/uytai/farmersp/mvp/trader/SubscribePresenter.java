package com.example.uytai.farmersp.mvp.trader;

import com.example.uytai.farmersp.model.DangKyModel;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.NongDanService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by uytai on 6/2/2018.
 */

public class SubscribePresenter implements ISubscribe.Presenter {
    private final ISubscribe.View mSubscibeView;

    public SubscribePresenter(ISubscribe.View mSubscibeView) {
        this.mSubscibeView = mSubscibeView;
    }

    @Override
    public void requestGetSubscibed() {
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<List<DangKyModel>> call = nongDanService.getDangKy();
        call.enqueue(new Callback<List<DangKyModel>>() {
            @Override
            public void onResponse(Call<List<DangKyModel>> call, Response<List<DangKyModel>> response) {
                if(response!=null){
                    mSubscibeView.CheckSubscribedSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<DangKyModel>> call, Throwable t) {
                mSubscibeView.CheckSubscribedFail();
            }
        });
    }

    @Override
    public void requestGetRating(int idtl) {

    }
}
