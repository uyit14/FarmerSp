package com.example.uytai.farmersp.mvp.trader;

import com.example.uytai.farmersp.model.ThuongLaiModel;

import java.util.List;

/**
 * Created by uytai on 4/13/2018.
 */

public interface ITrader {
interface View{
        void getListTraderSuccess(List<ThuongLaiModel> thuongLaiModel);
        void getListTraderFail();
    void showDialogProgress();
    void dismissDialog();
    }
    //
    interface Presenter{
        void requestGetListTrader();
}
}
