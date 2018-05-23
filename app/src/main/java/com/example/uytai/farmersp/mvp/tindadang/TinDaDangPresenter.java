package com.example.uytai.farmersp.mvp.tindadang;

import android.util.Log;

import com.example.uytai.farmersp.MainActivity;
import com.example.uytai.farmersp.model.NongSan;
import com.example.uytai.farmersp.mvp.feed.IFeed;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.NongSanService;

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
        NongSanService nongSanService = ApiClient.getClient().create(NongSanService.class);
        Call<List<NongSan>> call = nongSanService.getNongSanbyIDND(MainActivity.userModel.getId());
        call.enqueue(new Callback<List<NongSan>>() {
            @Override
            public void onResponse(Call<List<NongSan>> call, Response<List<NongSan>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        mTinDangView.getListNongSanSuccess(response.body());
                    }else{
                        Log.d("uytai123", "respone_null");
                    }
                }else{
                    Log.d("uytai123", "respone_null_" +MainActivity.userModel.getId());
                    Log.d("uytai123", "respone_fail");
                }
            }

            @Override
            public void onFailure(Call<List<NongSan>> call, Throwable t) {
                mTinDangView.getListNongSanFail();
            }
        });
    }
}
