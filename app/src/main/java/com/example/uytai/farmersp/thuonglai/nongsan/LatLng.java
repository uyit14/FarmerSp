package com.example.uytai.farmersp.thuonglai.nongsan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LatLng {
    @SerializedName("tennongsan")
    @Expose
    private String tennongsanRS;
    @SerializedName("lat")
    @Expose
    private double latRS;
    @SerializedName("lng")
    @Expose
    private double lngRS;

    public String getTennongsanRS() {
        return tennongsanRS;
    }

    public void setTennongsanRS(String tennongsanRS) {
        this.tennongsanRS = tennongsanRS;
    }

    public double getLatRS() {
        return latRS;
    }

    public void setLatRS(double latRS) {
        this.latRS = latRS;
    }

    public double getLngRS() {
        return lngRS;
    }

    public void setLngRS(double lngRS) {
        this.lngRS = lngRS;
    }
}
