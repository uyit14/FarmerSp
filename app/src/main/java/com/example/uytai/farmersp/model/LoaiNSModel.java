package com.example.uytai.farmersp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by uytai on 5/28/2018.
 */

public class LoaiNSModel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("tenloains")
    @Expose
    private String tenloains;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenloains() {
        return tenloains;
    }

    public void setTenloains(String tenloains) {
        this.tenloains = tenloains;
    }
}
