package com.example.uytai.farmersp.mvp.tindadang;

import android.util.Log;

import com.example.uytai.farmersp.MainActivity;
import com.example.uytai.farmersp.model.NongSanModel;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.NongDanService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by uytai on 5/20/2018.
 */

public class TinDaDangPresenter implements ITinDaDang.Presenter {
    private final ITinDaDang.View mTinDangView;

    public TinDaDangPresenter(ITinDaDang.View mTinDangView) {
        this.mTinDangView = mTinDangView;
    }

    @Override
    public void requestGetListNongSan() {
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<List<NongSanModel>> call = nongDanService.getNongSanbyIDND(MainActivity.nongDanModel.getId());
        call.enqueue(new Callback<List<NongSanModel>>() {
            @Override
            public void onResponse(Call<List<NongSanModel>> call, Response<List<NongSanModel>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        mTinDangView.getListNongSanSuccess(response.body());
                    }else{
                        Log.d("uytai123", "respone_null");
                    }
                }else{
                    Log.d("uytai123", "respone_null_" +MainActivity.nongDanModel.getId());
                    Log.d("uytai123", "respone_fail");
                }
            }

            @Override
            public void onFailure(Call<List<NongSanModel>> call, Throwable t) {
                mTinDangView.getListNongSanFail();
            }
        });
    }
}
