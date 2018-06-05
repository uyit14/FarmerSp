package com.example.uytai.farmersp.mvp.trader;

import android.util.Log;

import com.example.uytai.farmersp.model.ThuongLaiModel;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.NongDanService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by uytai on 4/13/2018.
 */

public class TraderPresenter implements ITrader.Presenter {
    private final ITrader.View mTraderView;

    public TraderPresenter(ITrader.View mTraderView) {
        this.mTraderView = mTraderView;
    }

    @Override
    public void requestGetListTrader() {
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<List<ThuongLaiModel>> call = nongDanService.getTrader();
        call.enqueue(new Callback<List<ThuongLaiModel>>() {
            @Override
            public void onResponse(Call<List<ThuongLaiModel>> call, Response<List<ThuongLaiModel>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        Log.d("uytai123", response.body().size()+"");
                        mTraderView.getListTraderSuccess(response.body());
                    }else{
                        //null
                        Log.d("uytai123", response.body().size()+"");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ThuongLaiModel>> call, Throwable t) {
                Log.d("uytai123", t.getMessage()+"");
                mTraderView.getListTraderFail();
            }
        });
    }
}
