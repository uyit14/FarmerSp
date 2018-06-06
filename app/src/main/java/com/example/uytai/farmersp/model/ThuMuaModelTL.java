package com.example.uytai.farmersp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class ThuMuaModelTL implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("ten_nongsan")
    @Expose
    private String tenNongsan;
    @SerializedName("tg_batdau")
    @Expose
    private Date tgBatdau;
    @SerializedName("tg_ketthuc")
    @Expose
    private Date tgKetthuc;
    @SerializedName("gia_thapnhat")
    @Expose
    private String giaThapnhat;
    @SerializedName("gia_caonhat")
    @Expose
    private String giaCaonhat;
    @SerializedName("noithumua")
    @Expose
    private String noithumua;
    @SerializedName("lienhe")
    @Expose
    private String lienhe;
    @SerializedName("hinhanh")
    @Expose
    private String hinhanh;
    @SerializedName("mota")
    @Expose
    private String mota;
    @SerializedName("id_thuonglai")
    @Expose
    private String idThuonglai;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenNongsan() {
        return tenNongsan;
    }

    public void setTenNongsan(String tenNongsan) {
        this.tenNongsan = tenNongsan;
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

    public String getGiaThapnhat() {
        return giaThapnhat;
    }

    public void setGiaThapnhat(String giaThapnhat) {
        this.giaThapnhat = giaThapnhat;
    }

    public String getGiaCaonhat() {
        return giaCaonhat;
    }

    public void setGiaCaonhat(String giaCaonhat) {
        this.giaCaonhat = giaCaonhat;
    }

    public String getNoithumua() {
        return noithumua;
    }

    public void setNoithumua(String noithumua) {
        this.noithumua = noithumua;
    }

    public String getLienhe() {
        return lienhe;
    }

    public void setLienhe(String lienhe) {
        this.lienhe = lienhe;
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

    public String getIdThuonglai() {
        return idThuonglai;
    }

    public void setIdThuonglai(String idThuonglai) {
        this.idThuonglai = idThuonglai;
    }
}
