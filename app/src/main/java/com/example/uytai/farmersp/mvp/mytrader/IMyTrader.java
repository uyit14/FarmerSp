package com.example.uytai.farmersp.mvp.mytrader;

import com.example.uytai.farmersp.model.DangKyModel;

import java.util.List;

/**
 * Created by uytai on 6/2/2018.
 */

public interface IMyTrader {
    interface View{
        void getIDTLSuccess(List<DangKyModel> dangKyModels);
        void getIDNDFail();
    }
    interface Presenter{
        void requestGetListIDTL();
    }
}
