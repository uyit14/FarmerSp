package com.example.uytai.farmersp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by uytai on 6/15/2018.
 */

public class NongSanModelTL implements Serializable {
    @SerializedName("ten")
    @Expose
    private String ten;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("tennongsan")
    @Expose
    private String tennongsan;
    @SerializedName("tg_batdau")
    @Expose
    private Date tgBatdau;
    @SerializedName("tg_ketthuc")
    @Expose
    private Date tgKetthuc;
    @SerializedName("ten_lh")
    @Expose
    private String tenLh;
    @SerializedName("sdt_lh")
    @Expose
    private String sdtLh;
    @SerializedName("diachi")
    @Expose
    private String diachi;
    @SerializedName("hinhanh")
    @Expose
    private String hinhanh;
    @SerializedName("mota")
    @Expose
    private String mota;
    @SerializedName("id_nongdan")
    @Expose
    private int idNongdan;
    @SerializedName("id_loains")
    @Expose
    private int idLoains;
    @SerializedName("id_quanhuyen")
    @Expose
    private int idQuanhuyen;
    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lng")
    @Expose
    private double lng;


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTennongsan() {
        return tennongsan;
    }

    public void setTennongsan(String tennongsan) {
        this.tennongsan = tennongsan;
    }

    public Date getTgBatdau() {
        return tgBatdau;
    }

    public void setTgBatdau(Date tgBatdau) {
        this.tgBatdau = tgBatdau;
    }

    public Date getTgKetthuc() {
        return tgKetthuc;
    }

    public void setTgKetthuc(Date tgKetthuc) {
        this.tgKetthuc = tgKetthuc;
    }

    public String getTenLh() {
        return tenLh;
    }

    public void setTenLh(String tenLh) {
        this.tenLh = tenLh;
    }

    public String getSdtLh() {
        return sdtLh;
    }

    public void setSdtLh(String sdtLh) {
        this.sdtLh = sdtLh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getIdNongdan() {
        return idNongdan;
    }

    public void setIdNongdan(int idNongdan) {
        this.idNongdan = idNongdan;
    }

    public int getIdLoains() {
        return idLoains;
    }

    public void setIdLoains(int idLoains) {
        this.idLoains = idLoains;
    }

    public int getIdQuanhuyen() {
        return idQuanhuyen;
    }

    public void setIdQuanhuyen(int idQuanhuyen) {
        this.idQuanhuyen = idQuanhuyen;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
