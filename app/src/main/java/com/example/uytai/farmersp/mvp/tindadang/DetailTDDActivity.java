package com.example.uytai.farmersp.mvp.tindadang;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uytai.farmersp.MainActivity;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.NongSanModel;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.NongDanService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class DetailTDDActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.tennongsan_detailTDD)
    EditText EdtTenNongSan;

    @BindView(R.id.begin_date_detailTDD)
    EditText EdtBeginDate;

    @BindView(R.id.end_date_detailTDD)
    EditText EdtEndDate;

    @BindView(R.id.description_detailTDD)
    EditText EdtDescription;

    @BindView(R.id.img_nongsan_detailTDD)
    ImageView imgNongSan;

    @BindView(R.id.lienhe_detailTDD)
    EditText EdtSDTLienHe;

    @BindView(R.id.tenlh_detailTDD)
    EditText EdtTenLienHe;

    @BindView(R.id.diachi_detailTDD)
    EditText EdtDiaChi;

    @BindView(R.id.bar_detail)
    Toolbar toolbar;

    @BindView(R.id.edit_tdd)
    TextView tv_edit_tdd;

    @BindView(R.id.swip_edit_nongsan)
    SwipeRefreshLayout swipeRefreshLayout;

    ProgressDialog pDialog;

    int id;

    NongSanModel nongSan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tdd);
        ButterKnife.bind(this);
        pDialog = new ProgressDialog(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        //
        ActionToolbar();
        Bundle bundle = getIntent().getBundleExtra(Constant.KEY_PUT_BUNDLE);
        if(bundle!=null){
            NongSanModel nongSanEx = (NongSanModel) bundle.getSerializable(Constant.KEY_PUT_OBJECT);
            id = nongSanEx.getId();
        }
        requestGetListNongSan();
    }

    private void setInfor() {
        EdtDescription.setText(nongSan.getMoTa());
        EdtDiaChi.setText(nongSan.getDiaChi());
        EdtSDTLienHe.setText(nongSan.getSdtLienHe());
        EdtTenLienHe.setText(nongSan.getTenLienHe());
        EdtTenNongSan.setText(nongSan.getTenNongsan());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        EdtBeginDate.setText(df.format(nongSan.getTgBatdau()));
        EdtEndDate.setText(df.format(nongSan.getTgKetthuc()));
        Glide.with(getApplicationContext()).load(nongSan.getHinhAnh()).placeholder(R.drawable.farm).into(imgNongSan);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chi tiết");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Enable(){
        EdtEndDate.setEnabled(true);
        EdtBeginDate.setEnabled(true);
        EdtTenNongSan.setEnabled(true);
        EdtTenLienHe.setEnabled(true);
        EdtSDTLienHe.setEnabled(true);
        EdtDiaChi.setEnabled(true);
        EdtDescription.setEnabled(true);
        tv_edit_tdd.setText("Lưu");
    }
    private void Disible() {
        EdtEndDate.setEnabled(false);
        EdtBeginDate.setEnabled(false);
        EdtTenNongSan.setEnabled(false);
        EdtTenLienHe.setEnabled(false);
        EdtSDTLienHe.setEnabled(false);
        EdtDiaChi.setEnabled(false);
        EdtDescription.setEnabled(false);
        tv_edit_tdd.setText("Sửa");
    }

    //
    public void requestGetListNongSan() {
        pDialog.setMessage("Đang tải thông tin...!");
        pDialog.setCancelable(false);
        pDialog.show();
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<List<NongSanModel>> call = nongDanService.getNongSanbyID(id);
        call.enqueue(new Callback<List<NongSanModel>>() {
            @Override
            public void onResponse(Call<List<NongSanModel>> call, Response<List<NongSanModel>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        nongSan = response.body().get(0);
                        setInfor();
                        if(pDialog.isShowing())
                            pDialog.dismiss();
                    }else{
                        if(pDialog.isShowing())
                        pDialog.dismiss();
                    }
                }else{
                    if(pDialog.isShowing())
                        pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<NongSanModel>> call, Throwable t) {
                Log.d("uyuyuy", "fail" + t.getMessage());
                if(pDialog.isShowing())
                    pDialog.dismiss();
            }
        });
    }


//    private void getTDDDetail() {
//        Bundle bundle = getIntent().getBundleExtra(Constant.KEY_PUT_BUNDLE);
//        if(bundle!=null){
//            NongSanModel nongSan = (NongSanModel) bundle.getSerializable(Constant.KEY_PUT_OBJECT);
//            id = nongSan.getId();
//            hinhanh = nongSan.getHinhAnh();
//            idnd = nongSan.getIdNongDan();
//            idloains = nongSan.getIdLoaiNS();
//            idqh = nongSan.getIdQuanHuyen();
//            EdtDescription.setText(nongSan.getMoTa());
//            EdtDiaChi.setText(nongSan.getDiaChi());
//            EdtSDTLienHe.setText(nongSan.getSdtLienHe());
//            EdtTenLienHe.setText(nongSan.getTenLienHe());
//            EdtTenNongSan.setText(nongSan.getTenNongsan());
//            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//            EdtBeginDate.setText(df.format(nongSan.getTgBatdau()));
//            EdtEndDate.setText(df.format(nongSan.getTgKetthuc()));
//            Glide.with(getApplicationContext()).load(nongSan.getHinhAnh()).placeholder(R.drawable.farm).into(imgNongSan);
//        }
//    }

    //
    private void UpdateTDD(){
        pDialog.setMessage("Đang tải thông tin...!");
        pDialog.setCancelable(false);
        pDialog.show();
        String mota = EdtDescription.getText().toString();
        String diachi = EdtDiaChi.getText().toString();
        String sdtlh = EdtSDTLienHe.getText().toString();
        String tenlh = EdtTenLienHe.getText().toString();
        String tenns = EdtTenNongSan.getText().toString();
        String begindate = EdtBeginDate.getText().toString();
        String enddate = EdtEndDate.getText().toString();
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<POST> call = nongDanService.edittindadang(id, tenns, begindate, enddate, tenlh, sdtlh, diachi,nongSan.getHinhAnh(), mota, nongSan.getIdNongDan()
                , nongSan.getIdLoaiNS(), nongSan.getIdQuanHuyen());
        call.enqueue(new Callback<POST>() {
            @Override
            public void onResponse(Call<POST> call, Response<POST> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    if(pDialog.isShowing())
                        pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<POST> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                if(pDialog.isShowing())
                    pDialog.dismiss();
            }
        });
        //
        Disible();
        finish();
        startActivity(getIntent());
    }

    //
    @OnClick(R.id.edit_tdd)
    void Edit(){
        switch (tv_edit_tdd.getText().toString()){
            case "Sửa":
                Enable();
                break;
            case "Lưu":
                UpdateTDD();
                break;
                default:
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                requestGetListNongSan();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
