package com.example.uytai.farmersp.thuonglai.TinDangTL;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.ThuMuaModelTL;
import com.example.uytai.farmersp.mvp.tindadang.TinDaDangActivity;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.ThuonglaiService;
import com.example.uytai.farmersp.thuonglai.MainTLActivity;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class DetailTLActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.tennongsan)
    EditText edtTenns;
    @BindView(R.id.ngaybatdau)
    EditText edtNgatbd;
    @BindView(R.id.noithumua)
    EditText edtNoithumua;
    @BindView(R.id.lienhe)
    EditText edttLienhe;
    @BindView(R.id.giathap)
    EditText edtGiathap;
    @BindView(R.id.giacao)
    EditText edtGiaCao;
    @BindView(R.id.mota)
    EditText edtMota;
    @BindView(R.id.hinhanh)
    ImageView imgHinhAnh;
    @BindView(R.id.edit)
    TextView tvEdit;
    @BindView(R.id.bar_detail)
    Toolbar toolbar;
    @BindView(R.id.swip_edit_thumua)
    SwipeRefreshLayout swipeRefreshLayout;
    ThuMuaModelTL thuMuaModelTLS;
    int id = 0;

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tl);
        ButterKnife.bind(this);
        pDialog = new ProgressDialog(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        Bundle bundle = getIntent().getBundleExtra(Constant.KEY_PUT_BUNDLE);
        if(bundle!=null){
            ThuMuaModelTL thumuas = (ThuMuaModelTL) bundle.getSerializable(Constant.KEY_PUT_OBJECT);
            id = thumuas.getId();
        }
        int ISND = getIntent().getIntExtra("ISND", -1);
        if(ISND==100){
            tvEdit.setVisibility(View.GONE);
        }else{
            tvEdit.setVisibility(View.VISIBLE);
        }
        ActionToolbar();
        requestGetListThuMua();
    }

    private void setInfor() {
        edttLienhe.setText(thuMuaModelTLS.getLienhe());
        edtTenns.setText(thuMuaModelTLS.getTenNongsan());
        edtNoithumua.setText(thuMuaModelTLS.getNoithumua());
        edtMota.setText(thuMuaModelTLS.getMota());
//            DecimalFormat formatter = new DecimalFormat("###,###,###");
//            String giathapnhat = formatter.format(Double.parseDouble(String.valueOf(thumuas.getGiaThapnhat())));
//            String giacaonhat =  formatter.format(Double.parseDouble(String.valueOf(thumuas.getGiaCaonhat())));
        edtGiathap.setText(thuMuaModelTLS.getGiaThapnhat()+"");
        edtGiaCao.setText(thuMuaModelTLS.getGiaCaonhat()+"");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        edtNgatbd.setText(df.format(thuMuaModelTLS.getTgBatdau()));
        Glide.with(getApplicationContext()).load(thuMuaModelTLS.getHinhanh()).placeholder(R.drawable.logo_design).into(imgHinhAnh);
    }

    private void requestGetListThuMua() {
        pDialog.setMessage("Đang tải thông tin...!");
        pDialog.setCancelable(false);
        pDialog.show();
        ThuonglaiService thuonglaiService = ApiClient.getClient().create(ThuonglaiService.class);
        Call<List<ThuMuaModelTL>> call = thuonglaiService.getThuMuabyIDThuMua(id);
        call.enqueue(new Callback<List<ThuMuaModelTL>>() {
            @Override
            public void onResponse(Call<List<ThuMuaModelTL>> call, Response<List<ThuMuaModelTL>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        thuMuaModelTLS = response.body().get(0);
                        setInfor();
                        if(pDialog.isShowing())
                            pDialog.dismiss();
                    }else{
                       if(pDialog.isShowing())
                        pDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ThuMuaModelTL>> call, Throwable t) {
//                Log.d("uytai123", "Get list fail");
                if(pDialog.isShowing())
                    pDialog.dismiss();
            }
        });
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chi tiết");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //
    private void Enable(){
        edtGiaCao.setEnabled(true);
        edtGiathap.setEnabled(true);
        edtMota.setEnabled(true);
        edtNgatbd.setEnabled(true);
        edtNoithumua.setEnabled(true);
        edtTenns.setEnabled(true);
        edttLienhe.setEnabled(true);
        tvEdit.setText("Lưu");
    }
    private void Disible() {
        edtGiaCao.setEnabled(false);
        edtGiathap.setEnabled(false);
        edtMota.setEnabled(false);
        edtNgatbd.setEnabled(false);
        edtNoithumua.setEnabled(false);
        edtTenns.setEnabled(false);
        edttLienhe.setEnabled(false);
        tvEdit.setText("Sửa");
    }

    //
    //
    @OnClick(R.id.edit)
    void Edit(){
        switch (tvEdit.getText().toString()){
            case "Sửa":
                Enable();
                break;
            case "Lưu":
                UpdateTDD();
                break;
            default:
        }
    }

    private void UpdateTDD() {
        String lienhe = edttLienhe.getText().toString();
        String mota = edtMota.getText().toString();
        String ngaybatdau = edtNgatbd.getText().toString();
        String tenns = edtTenns.getText().toString();
        String noithumua = edtNoithumua.getText().toString();
        double giacaonhat = Double.parseDouble(edtGiaCao.getText().toString());
        double giathapnhat = Double.parseDouble(edtGiathap.getText().toString());
        String ngayketthuc = String.valueOf(thuMuaModelTLS.getTgKetthuc());
        ThuonglaiService thuonglaiService = ApiClient.getClient().create(ThuonglaiService.class);
        Call<POST> call = thuonglaiService.editthumua(id, tenns, ngaybatdau, ngayketthuc, giathapnhat,
                giacaonhat, noithumua, lienhe, thuMuaModelTLS.getHinhanh(), mota, MainTLActivity.thuongLaiModel.getId(), 1);
        call.enqueue(new Callback<POST>() {
            @Override
            public void onResponse(Call<POST> call, Response<POST> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<POST> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
            }
        });
        //
        Disible();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                requestGetListThuMua();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
