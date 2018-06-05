package com.example.uytai.farmersp.respone;

import com.example.uytai.farmersp.model.ThuongLaiModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by uytai on 4/13/2018.
 */

public class TraderRespone {
    @SerializedName("result")
    private List<ThuongLaiModel> result;

    public List<ThuongLaiModel> getResult() {
        return result;
    }

    public void setResult(List<ThuongLaiModel> result) {
        this.result = result;
    }
}
