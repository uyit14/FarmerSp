package com.example.uytai.farmersp.mvp.trader;

import com.example.uytai.farmersp.model.TraderModel;
import com.example.uytai.farmersp.respone.TraderRespone;

import java.util.List;

/**
 * Created by uytai on 4/13/2018.
 */

public interface ITrader {
interface View{
        void getListTraderSuccess(List<TraderModel> traderModel);
        void getListTraderFail();
    }
    //
    interface Presenter{
        void requestGetListTrader();
}
}
