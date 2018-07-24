package com.example.uytai.farmersp.mvp.trader;

import com.example.uytai.farmersp.model.DangKyModel;
import com.example.uytai.farmersp.model.Rating;

import java.util.List;

/**
 * Created by uytai on 6/2/2018.
 */

public interface ISubscribe {
    interface View{
        void CheckSubscribedSuccess(List<DangKyModel> dangKyModels);
        void CheckSubscribedFail();
        void GetRatingSuccess(List<Rating> ratings);
    }
    interface Presenter{
        void requestGetSubscibed();
        void requestGetRating(int idtl);
    }
}
