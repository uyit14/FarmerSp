package com.example.uytai.farmersp.mvp.register;

/**
 * Created by uytai on 5/24/2018.
 */

public interface IRegister {
    interface View{
        void SignUpSuccess();
        void SignUpFail();
    }
    interface Presenter{
        void requestSignUp();
        void requestSignUpThuonglai();
    }
}
