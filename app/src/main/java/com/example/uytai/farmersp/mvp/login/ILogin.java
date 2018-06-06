package com.example.uytai.farmersp.mvp.login;

import com.example.uytai.farmersp.model.NongDanModel;
import com.example.uytai.farmersp.model.ThuongLaiModel;

import java.util.List;

/**
 * Created by uytai on 4/16/2018.
 */

public interface ILogin {
    interface View{
        void getListUserSuccess(List<NongDanModel> nongDanModels);
        void getListUserFail();
        void getListTLSuccess(List<ThuongLaiModel> thuongLaiModels);
        void getListTLFail();
    }
    interface Presenter{
        void requestGetListUser();
        void requestGetThuongLai();
    }
}
