package com.example.uytai.farmersp.mvp.trader;

import com.example.uytai.farmersp.model.DangKyModel;

import java.util.List;

/**
 * Created by uytai on 6/2/2018.
 */

public interface ISubscribe {
    interface View{
        void CheckSubscribedSuccess(List<DangKyModel> dangKyModels);
        void CheckSubscribedFail();
    }
    interface Presenter{
        void requestGetSubscibed();
    }
}
