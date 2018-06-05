package com.example.uytai.farmersp.mvp.feed;


import com.example.uytai.farmersp.model.ThuMuaModel;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.NongDanService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by uytai on 4/15/2018.
 */

public class FeedPresenter implements IFeed.Presenter {
    private final IFeed.View mFeedView;

    public FeedPresenter(IFeed.View mFeedView) {
        this.mFeedView = mFeedView;
    }

    @Override
    public void requestGetListFeed() {
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<List<ThuMuaModel>> call = nongDanService.getFeedThuMua();
        call.enqueue(new Callback<List<ThuMuaModel>>() {
            @Override
            public void onResponse(Call<List<ThuMuaModel>> call, Response<List<ThuMuaModel>> response) {
                if(response.body()!=null){
                    mFeedView.getListFeedSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ThuMuaModel>> call, Throwable t) {
                mFeedView.getListFeedFail();
            }
        });
    }
}
