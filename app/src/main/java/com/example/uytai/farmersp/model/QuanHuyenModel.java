package com.example.uytai.farmersp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by uytai on 5/29/2018.
 */

public class QuanHuyenModel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("tenquanhuyen")
    @Expose
    private String tenquanhuyen;
    @SerializedName("id_tinhthanh")
    @Expose
    private String id_tinhthanh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenquanhuyen() {
        return tenquanhuyen;
    }

    public void setTenquanhuyen(String tenquanhuyen) {
        this.tenquanhuyen = tenquanhuyen;
    }

    public String getId_tinhthanh() {
        return id_tinhthanh;
    }

    public void setId_tinhthanh(String id_tinhthanh) {
        this.id_tinhthanh = id_tinhthanh;
    }
}
