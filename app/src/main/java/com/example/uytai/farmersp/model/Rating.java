package com.example.uytai.farmersp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating {
    @SerializedName("idbd")
    @Expose
    private int idbd;
    @SerializedName("id_user_rt")
    @Expose
    private int id_user_rt;
    @SerializedName("rate")
    @Expose
    private float rate;
    @SerializedName("id_user")
    @Expose
    private int id_user;

    public int getIdbd() {
        return idbd;
    }

    public void setIdbd(int idbd) {
        this.idbd = idbd;
    }

    public int getId_user_rt() {
        return id_user_rt;
    }

    public void setId_user_rt(int id_user_rt) {
        this.id_user_rt = id_user_rt;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
