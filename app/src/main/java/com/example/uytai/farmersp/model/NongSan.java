package com.example.uytai.farmersp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by uytai on 5/20/2018.
 */

public class NongSan {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("ten_nongsan")
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
    private String idNongDan;

    @SerializedName("id_loains")
    @Expose
    private String idLoaiNS;

    @SerializedName("id_quanhuyen")
    @Expose
    private String idQuanHuyen;
}
