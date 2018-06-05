package com.example.uytai.farmersp.mvp.tindadang;
import com.example.uytai.farmersp.model.NongSanModel;
import java.util.List;

/**
 * Created by uytai on 5/20/2018.
 */

public interface ITinDaDang {
    interface View {
        void getListNongSanSuccess(List<NongSanModel> nongSanModels);
        void getListNongSanFail();
    }
    interface Presenter{
        void requestGetListNongSan();
    }
}
