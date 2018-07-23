package com.example.uytai.farmersp.mvp.dangtin;

import android.util.Log;

import com.example.uytai.farmersp.model.LoaiNSModel;
import com.example.uytai.farmersp.model.NongSanModel;
import com.example.uytai.farmersp.model.QuanHuyenModel;
import com.example.uytai.farmersp.model.TinhThanhModel;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.NongDanService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by uytai on 5/28/2018.
 */

public class DangTinPresenter implements IDangTin.Presenter {
    public final IDangTin.View mDangTinView;

    public DangTinPresenter(IDangTin.View mDangTinView) {
        this.mDangTinView = mDangTinView;
    }

    @Override
    public void requestDangTin() {
        Log.d("uytai", DangTinActivity.lat+"_");
        mDangTinView.showDialogProgress();
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<List<NongSanModel>> call = nongDanService.themnongsan(DangTinActivity.tennongsan, DangTinActivity.tg_batdau, DangTinActivity.tg_ketthuc,
                DangTinActivity.ten_lh, DangTinActivity.sdt_lh, DangTinActivity.diachi, DangTinActivity.hinhanh, DangTinActivity.mota,
                DangTinActivity.id_nd, DangTinActivity.id_loains_them, DangTinActivity.id_quanhuyen_them, DangTinActivity.lat, DangTinActivity.lng);
        call.enqueue(new Callback<List<NongSanModel>>() {
            @Override
            public void onResponse(Call<List<NongSanModel>> call, Response<List<NongSanModel>> response) {
                mDangTinView.DangTinSuccess();
                mDangTinView.dismissDialog();
            }

            @Override
            public void onFailure(Call<List<NongSanModel>> call, Throwable t) {
                mDangTinView.DangTinSuccess();
                mDangTinView.dismissDialog();
            }
        });
    }

    @Override
    public void requestGetLoaiNS() {
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<List<LoaiNSModel>> call = nongDanService.getLoains();
        call.enqueue(new Callback<List<LoaiNSModel>>() {
            @Override
            public void onResponse(Call<List<LoaiNSModel>> call, Response<List<LoaiNSModel>> response) {
                if(response.body()!=null){
                    mDangTinView.getLoaiNSSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<LoaiNSModel>> call, Throwable t) {
                mDangTinView.getLoaiNsFail();
            }
        });
    }

    @Override
    public void requestGetTinhThanh() {
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<List<TinhThanhModel>> call = nongDanService.getTinhThanh();
        call.enqueue(new Callback<List<TinhThanhModel>>() {
            @Override
            public void onResponse(Call<List<TinhThanhModel>> call, Response<List<TinhThanhModel>> response) {
                if(response!=null){
                    mDangTinView.getTinhThanhSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<TinhThanhModel>> call, Throwable t) {
                mDangTinView.DangTinFail();
            }
        });
    }

    @Override
    public void requestGetQuanHuyen(int idtt) {
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<List<QuanHuyenModel>> call = nongDanService.getQuanHuyen(idtt);
        call.enqueue(new Callback<List<QuanHuyenModel>>() {
            @Override
            public void onResponse(Call<List<QuanHuyenModel>> call, Response<List<QuanHuyenModel>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        mDangTinView.getQuanHuyenSuccess(response.body());
                    }else{
//                        Log.d("uytai123", "NULL");
                    }
                }else{
//                    Log.d("uytai123", "FAIL");
                }

            }

            @Override
            public void onFailure(Call<List<QuanHuyenModel>> call, Throwable t) {
                mDangTinView.getQuanHuyenFail();
            }
        });
    }
}
