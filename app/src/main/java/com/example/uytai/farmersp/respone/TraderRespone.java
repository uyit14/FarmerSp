package com.example.uytai.farmersp.respone;

import com.example.uytai.farmersp.model.TraderModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by uytai on 4/13/2018.
 */

public class TraderRespone {
    @SerializedName("result")
    private List<TraderModel> result;

    public List<TraderModel> getResult() {
        return result;
    }

    public void setResult(List<TraderModel> result) {
        this.result = result;
    }
}
