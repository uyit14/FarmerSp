package com.example.uytai.farmersp.mvp.mytrader;

import com.example.uytai.farmersp.MainActivity;
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

public class MyTraderPresenter implements IMyTrader.Presenter {
    private final IMyTrader.View mMytraderView;

    public MyTraderPresenter(IMyTrader.View mMytraderView) {
        this.mMytraderView = mMytraderView;
    }

    @Override
    public void requestGetListIDTL() {
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<List<DangKyModel>> call = nongDanService.getIDTL(MainActivity.nongDanModel.getId());
        call.enqueue(new Callback<List<DangKyModel>>() {
            @Override
            public void onResponse(Call<List<DangKyModel>> call, Response<List<DangKyModel>> response) {
                if(response!=null){
                    mMytraderView.getIDTLSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<DangKyModel>> call, Throwable t) {
                mMytraderView.getIDNDFail();
            }
        });
    }
}
