package com.example.uytai.farmersp.mvp.tindadang;

import com.example.uytai.farmersp.model.NongSan;

import java.util.List;

/**
 * Created by uytai on 5/20/2018.
 */

public interface ITinDaDang {
    interface View {
        void getListNongSanSuccess(List<NongSan> nongSanModels);
        void getListNongSanFail();
    }
    interface Presenter{
        void requestGetListNongSan();
    }
}
