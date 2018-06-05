package com.example.uytai.farmersp.mvp.login;

import com.example.uytai.farmersp.model.NongDanModel;

import java.util.List;

/**
 * Created by uytai on 4/16/2018.
 */

public interface ILogin {
    interface View{
        void getListUserSuccess(List<NongDanModel> nongDanModels);
        void getListUserFail();
    }
    interface Presenter{
        void requestGetListUser();
    }
}
