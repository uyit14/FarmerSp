package com.example.uytai.farmersp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by uytai on 5/20/2018.
 */

public class NongSanModel implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("tennongsan")
    @Expose
    private String tenNongsan;

    @SerializedName("tg_batdau")
    @Expose
    private Date tgBatdau;

    @SerializedName("tg_ketthuc")
    @Expose
    private Date tgKetthuc;

    @SerializedName("ten_lh")
    @Expose
    private String tenLienHe;

    @SerializedName("sdt_lh")
    @Expose
    private String sdtLienHe;

    @SerializedName("diachi")
    @Expose
    private String diaChi;

    @SerializedName("hinhanh")
    @Expose
    private String hinhAnh;

    @SerializedName("mota")
    @Expose
    private String moTa;

    @SerializedName("id_nongdan")
    @Expose
    private int idNongDan;

    @SerializedName("id_loains")
    @Expose
    private int idLoaiNS;

    @SerializedName("id_quanhuyen")
    @Expose
    private int idQuanHuyen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getTenLienHe() {
        return tenLienHe;
    }

    public void setTenLienHe(String tenLienHe) {
        this.tenLienHe = tenLienHe;
    }

    public String getSdtLienHe() {
        return sdtLienHe;
    }

    public void setSdtLienHe(String sdtLienHe) {
        this.sdtLienHe = sdtLienHe;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getIdNongDan() {
        return idNongDan;
    }

    public void setIdNongDan(int idNongDan) {
        this.idNongDan = idNongDan;
    }

    public int getIdLoaiNS() {
        return idLoaiNS;
    }

    public void setIdLoaiNS(int idLoaiNS) {
        this.idLoaiNS = idLoaiNS;
    }

    public int getIdQuanHuyen() {
        return idQuanHuyen;
    }

    public void setIdQuanHuyen(int idQuanHuyen) {
        this.idQuanHuyen = idQuanHuyen;
    }
}
