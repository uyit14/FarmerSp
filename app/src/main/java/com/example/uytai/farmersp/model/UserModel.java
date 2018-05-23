package com.example.uytai.farmersp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by uytai on 4/16/2018.
 */

public class UserModel implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("ten")
    @Expose
    private String ten;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sdt")
    @Expose
    private String sdt;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("taikhoan")
    @Expose
    private String taikhoan;
    @SerializedName("matkhau")
    @Expose
    private String matkhau;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSdt(){return sdt;}

    public void setSdt(String sdt){
        this.sdt = sdt;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
