package com.example.uytai.farmersp.mvp.register;

import com.example.uytai.farmersp.model.NongDanModel;
import com.example.uytai.farmersp.model.ThuongLaiModel;

import java.util.List;

/**
 * Created by uytai on 5/24/2018.
 */

public interface IRegister {
    interface View{
        void SignUpSuccess();
        void SignUpFail();
        void getLisNongDanSuccess(List<NongDanModel> nongDanModels);
        void getListThuongLaiSuccess(List<ThuongLaiModel> thuongLaiModels);
    }
    interface Presenter{
        void requestSignUp();
        void requestSignUpThuonglai();
        void requestGetNongDan();
        void requestGetThuongLai();
    }
}
