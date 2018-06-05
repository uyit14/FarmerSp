package com.example.uytai.farmersp.mvp.tindadang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.NongSanModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailTDDActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tdd);
        ButterKnife.bind(this);
        //
        ActionToolbar();
        getTDDDetail();
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

    private void getTDDDetail() {
        Bundle bundle = getIntent().getBundleExtra(Constant.KEY_PUT_BUNDLE);
        if(bundle!=null){
            NongSanModel nongSan = (NongSanModel) bundle.getSerializable(Constant.KEY_PUT_OBJECT);
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
    }
}
