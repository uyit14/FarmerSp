package com.example.uytai.farmersp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by uytai on 5/29/2018.
 */

public class TinhThanhModel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("tentinhthanh")
    @Expose
    private String tentinhthanh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTentinhthanh() {
        return tentinhthanh;
    }

    public void setTentinhthanh(String tentinhthanh) {
        this.tentinhthanh = tentinhthanh;
    }
}
