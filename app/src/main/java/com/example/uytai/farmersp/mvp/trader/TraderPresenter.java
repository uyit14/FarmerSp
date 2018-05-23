package com.example.uytai.farmersp.mvp.trader;

import android.util.Log;

import com.example.uytai.farmersp.model.TraderModel;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.TraderService;

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
        TraderService traderService = ApiClient.getClient().create(TraderService.class);
        Call<List<TraderModel>> call = traderService.getTrader();
        call.enqueue(new Callback<List<TraderModel>>() {
            @Override
            public void onResponse(Call<List<TraderModel>> call, Response<List<TraderModel>> response) {
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
            public void onFailure(Call<List<TraderModel>> call, Throwable t) {
                Log.d("uytai123", t.getMessage()+"");
                mTraderView.getListTraderFail();
            }
        });
    }
}
