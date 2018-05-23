package com.example.uytai.farmersp.mvp.feed;

import com.example.uytai.farmersp.model.FeedModel;

import java.util.List;

/**
 * Created by uytai on 4/15/2018.
 */

public interface IFeed {
interface View {
    void getListFeedSuccess(List<FeedModel> feedModels);
    void getListFeedFail();
}
interface Presenter{
    void requestGetListFeed();
}
}
