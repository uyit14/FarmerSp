package com.example.uytai.farmersp.mvp.dangtin;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uytai.farmersp.MainActivity;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.model.LoaiNSModel;
import com.example.uytai.farmersp.model.QuanHuyenModel;
import com.example.uytai.farmersp.model.TinhThanhModel;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DangTinActivity extends AppCompatActivity implements IDangTin.View {

    //
    public static String tennongsan="";
    public static String tg_batdau = "";
    public static String tg_ketthuc = "";
    public static String ten_lh="";
    public static String sdt_lh="";
    public static String diachi="";
    public static String hinhanh="";
    public static String mota="";
    public static int id_nd=0;
    public static int id_loains_them=0;
    public static int id_quanhuyen_them=0;
    //
    @BindView(R.id.tennongsan_dangtin)
    EditText edt_tennongsan;
    @BindView(R.id.ngaybatdau_dangtin)
    EditText edt_ngaybatdau;
    @BindView(R.id.ngayketthuc_dangtin)
    EditText edt_ngayketthuc;
    @BindView(R.id.tenlienhe_dangtin)
    EditText edt_tenlienhe;
    @BindView(R.id.sdt_dangtin)
    EditText edt_sdt;
    @BindView(R.id.diachi_dangtin)
    EditText edt_diachi;
    @BindView(R.id.hinhanh_dangtin)
    ImageView img_hinhanh;
    @BindView(R.id.mota_dangtin)
    EditText edt_mota;
    @BindView(R.id.sploains_dangtin)
    Spinner sp_loains;
    @BindView(R.id.spquanhuyen_dangtin)
    Spinner sp_quanhuyen;
    @BindView(R.id.sptinhthanh_dangtin)
    Spinner sp_tinhthanh;
    @BindView(R.id.btn_huy_dangtin)
    Button btn_huy;
    @BindView(R.id.btn_luu_dangtin)
    Button btn_luu;
    @BindView(R.id.them_canban_toolbar)
    Toolbar toolbar;

    DangTinPresenter dangTinPresenter;

    //
    ArrayList<String> arrLoains;
    ArrayList<String> arrTinhThanh;
    ArrayList<String> arrQuanHuyen;
    ArrayAdapter<String> QHadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_tin);
        ButterKnife.bind(this);
        dangTinPresenter = new DangTinPresenter(this);
        dangTinPresenter.requestGetLoaiNS();
        dangTinPresenter.requestGetTinhThanh();
        //
        arrLoains = new ArrayList<>();
        arrTinhThanh = new ArrayList<>();
        arrQuanHuyen = new ArrayList<>();
        QHadapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, arrQuanHuyen);
        //
        ActionToolbar();
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cá nhân");
    }

    private void setClickonSpinner() {
        sp_tinhthanh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                QHadapter.clear();
                dangTinPresenter.requestGetQuanHuyen(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getDataDangTin(){
        sp_loains.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_loains_them = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //
        sp_quanhuyen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_quanhuyen_them = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //
        id_nd = MainActivity.nongDanModel.getId();
        tennongsan = edt_tennongsan.getText().toString();
        tg_batdau = edt_ngaybatdau.getText().toString();
        tg_ketthuc = edt_ngayketthuc.getText().toString();
        ten_lh = edt_tenlienhe.getText().toString();
        sdt_lh = edt_sdt.getText().toString();
        diachi = edt_diachi.getText().toString();
        mota = edt_mota.getText().toString();
        if(tennongsan.equals("") || tg_batdau.equals("") || tg_ketthuc.equals("")
                || sdt_lh.equals("") || ten_lh.equals("") || diachi.equals("")
                || mota.equals("")){
            Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }else{
            dangTinPresenter.requestDangTin();
        }
    }

    @Override
    public void DangTinSuccess() {
        Toast.makeText(getApplicationContext(), "Đăng tin thành công!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DangTinFail() {
        Toast.makeText(getApplicationContext(), "Đăng tin thất bại, vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getLoaiNSSuccess(List<LoaiNSModel> loaiNSModels) {
        if(loaiNSModels!=null){
            for(int i = 0 ; i<loaiNSModels.size(); i++){
                arrLoains.add(loaiNSModels.get(i).getTenloains());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item, arrLoains);
                sp_loains.setAdapter(adapter);
            }
        }
    }

    @Override
    public void getLoaiNsFail() {
        Toast.makeText(getApplicationContext(), "Tải thất bại, vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getTinhThanhSuccess(List<TinhThanhModel> tinhthanhModels) {
        for(int i = 0 ; i<tinhthanhModels.size(); i++){
            arrTinhThanh.add(tinhthanhModels.get(i).getTentinhthanh());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_spinner_dropdown_item, arrTinhThanh);
            sp_tinhthanh.setAdapter(adapter);
            //
            setClickonSpinner();
        }
    }

    @Override
    public void getTinhThanhFail() {
        Toast.makeText(getApplicationContext(), "Tải thất bại, vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getQuanHuyenSuccess(List<QuanHuyenModel> quanHuyenModels) {
        if(quanHuyenModels!=null){
            Log.d("uytai123", arrQuanHuyen.size()+"");
            for(int i = 0 ; i<quanHuyenModels.size(); i++){
                arrQuanHuyen.add(quanHuyenModels.get(i).getTenquanhuyen());
                sp_quanhuyen.setAdapter(QHadapter);
            }
        }else{
            Log.d("uytai123", "NULL");
        }
    }

    @Override
    public void getQuanHuyenFail() {
        Toast.makeText(getApplicationContext(), "Tải thất bại, vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
    }

    // ------- cac su kien click --------- //
    @OnClick(R.id.hinhanh_dangtin)
    void ChooseImage(View v){
        onPickPhoto(v);
    }

    @OnClick(R.id.btn_huy_dangtin)
    void HuyDangTin(View v){
        finish();
    }

    @OnClick(R.id.btn_luu_dangtin)
    void LuuDangTin(View v){
        getDataDangTin();
    }

    //----------- chon anh tu floder --------------//
    private static final int GALLERY_PICK = 1;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case GALLERY_PICK:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent, GALLERY_PICK);
                }else{
                    //ban khong cho phep mo camera
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //
    //chon anh
    public void onPickPhoto(View view){
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galleryIntent, "SELECT IMAGE"),GALLERY_PICK);
    }

    //

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GALLERY_PICK && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            CropImage.activity(imageUri).setAspectRatio(1, 1).start(this);
        }
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                Uri resultUri = result.getUri();
                File file = new File(resultUri.getPath());
                Glide.with(DangTinActivity.this).load(file).into(img_hinhanh);
                hinhanh = String.valueOf(file);
                Log.d("uytai123", hinhanh);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
