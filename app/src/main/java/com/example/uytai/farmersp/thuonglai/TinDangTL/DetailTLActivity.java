package com.example.uytai.farmersp.thuonglai.TinDangTL;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.ThuMuaModelTL;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailTLActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tl);
        ButterKnife.bind(this);
        getTDDDetail();
    }

    private void getTDDDetail() {
        Bundle bundle = getIntent().getBundleExtra(Constant.KEY_PUT_BUNDLE);
        if(bundle!=null){
            ThuMuaModelTL thumua = (ThuMuaModelTL) bundle.getSerializable(Constant.KEY_PUT_OBJECT);
            edttLienhe.setText(thumua.getLienhe());
            edtTenns.setText(thumua.getTenNongsan());
            edtNoithumua.setText(thumua.getNoithumua());
            edtMota.setText(thumua.getMota());
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            String giathapnhat = formatter.format(Double.parseDouble(thumua.getGiaThapnhat()));
            String giacaonhat =  formatter.format(Double.parseDouble(thumua.getGiaCaonhat()));
            edtGiathap.setText(giathapnhat+" VND");
            edtGiaCao.setText(giacaonhat+" VND");
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            edtNgatbd.setText(df.format(thumua.getTgBatdau()));
            Glide.with(getApplicationContext()).load(thumua.getHinhanh()).placeholder(R.drawable.logo_design).into(imgHinhAnh);
        }
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
        Disible();
        finish();
        startActivity(getIntent());
    }
}
