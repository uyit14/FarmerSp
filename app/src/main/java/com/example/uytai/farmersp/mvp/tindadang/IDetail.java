package com.example.uytai.farmersp.mvp.tindadang;

import com.example.uytai.farmersp.model.NongSanModel;

import java.util.List;

/**
 * Created by uytai on 6/13/2018.
 */

public interface IDetail {
    interface View {
        void getNongSanSuccess(NongSanModel nongSanModels);
        void getNongSanFail();
    }
    interface Presenter{
        void requestGettNongSan();
    }
}
