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
    @SerializedName("ten_lh")
    @Expose
    private String tenlhRS;
    @SerializedName("sdt_lh")
    @Expose
    private String sdtlhRS;
    @SerializedName("mota")
    @Expose
    private String motaRS;
    @SerializedName("id_loains")
    @Expose
    private int id_loains;

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

    public String getTenlhRS() {
        return tenlhRS;
    }

    public void setTenlhRS(String tenlhRS) {
        this.tenlhRS = tenlhRS;
    }

    public String getSdtlhRS() {
        return sdtlhRS;
    }

    public void setSdtlhRS(String sdtlhRS) {
        this.sdtlhRS = sdtlhRS;
    }

    public String getMotaRS() {
        return motaRS;
    }

    public void setMotaRS(String motaRS) {
        this.motaRS = motaRS;
    }

    public int getId_loains() {
        return id_loains;
    }

    public void setId_loains(int id_loains) {
        this.id_loains = id_loains;
    }
}
