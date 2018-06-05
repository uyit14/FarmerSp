package com.example.uytai.farmersp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by uytai on 6/2/2018.
 */

public class DangKyModel {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("idnd")
    @Expose
    private int idnd;
    @SerializedName("idtl")
    @Expose
    private int idtl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdnd() {
        return idnd;
    }

    public void setIdnd(int idnd) {
        this.idnd = idnd;
    }

    public int getIdtl() {
        return idtl;
    }

    public void setIdtl(int idtl) {
        this.idtl = idtl;
    }
}
