package com.example.uytai.farmersp.mvp.feed;

import com.example.uytai.farmersp.model.ThuMuaModel;

import java.util.List;

/**
 * Created by uytai on 4/15/2018.
 */

public interface IFeed {
interface View {
    void getListFeedSuccess(List<ThuMuaModel> thuMuaModels);
    void getListFeedFail();
    void showDialogProgress();
    void dismissDialog();
}
interface Presenter{
    void requestGetListFeed();
}
}
