package com.example.uytai.farmersp.mvp.feed;


import com.example.uytai.farmersp.model.FeedModel;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.FeedService;

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
        FeedService feedService = ApiClient.getClient().create(FeedService.class);
        Call<List<FeedModel>> call = feedService.getFeedThuMua();
        call.enqueue(new Callback<List<FeedModel>>() {
            @Override
            public void onResponse(Call<List<FeedModel>> call, Response<List<FeedModel>> response) {
                if(response.body()!=null){
                    mFeedView.getListFeedSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<FeedModel>> call, Throwable t) {
                mFeedView.getListFeedFail();
            }
        });
    }
}
