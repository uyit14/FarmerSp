package com.example.uytai.farmersp.mvp.dangtin;

import com.example.uytai.farmersp.model.LoaiNSModel;
import com.example.uytai.farmersp.model.QuanHuyenModel;
import com.example.uytai.farmersp.model.TinhThanhModel;

import java.util.List;

/**
 * Created by uytai on 5/28/2018.
 */

public interface IDangTin {
    interface View{
        void DangTinSuccess();
        void DangTinFail();
        void getLoaiNSSuccess(List<LoaiNSModel> loaiNSModels);
        void getLoaiNsFail();
        void getTinhThanhSuccess(List<TinhThanhModel> tinhthanhModels);
        void getTinhThanhFail();
        void getQuanHuyenSuccess(List<QuanHuyenModel> quanHuyenModels);
        void getQuanHuyenFail();
        void showDialogProgress();
        void dismissDialog();
    }
    interface Presenter{
        void requestDangTin();
        void requestGetLoaiNS();
        void requestGetTinhThanh();
        void requestGetQuanHuyen(int idtt);
    }
}
