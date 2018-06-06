package com.example.uytai.farmersp.thuonglai.TinDangTL;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.ThuonglaiService;
import com.example.uytai.farmersp.thuonglai.MainTLActivity;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class DangTinTLActivity extends AppCompatActivity {
    @BindView(R.id.btn_huy_dangtin_tl)
    Button btnHuy;
    @BindView(R.id.btn_luu_dangtin_tl)
    Button btnLuu;
    @BindView(R.id.giacaonhat_dangtin_tl)
    EditText edtGiaCaoNhat;
    @BindView(R.id.giathapnhat_dangtin_tl)
    EditText edtGiaThapNhat;
    @BindView(R.id.hinhanh_dangtin_tl)
    ImageView imgHinhAnh;
    @BindView(R.id.lienhe_dangtin_tl)
    EditText edtLienHe;
    @BindView(R.id.ngaybatdau_dangtin_tl)
    EditText edtNgayBatDau;
    @BindView(R.id.ngayketthuc_dangtin_tl)
    EditText edtNgayKetThuc;
    @BindView(R.id.mota_dangtin_tl)
    EditText edtMoTa;
    @BindView(R.id.noithumua_dangtin_tl)
    EditText edtNoiThuMua;
    @BindView(R.id.tennongsan_dangtin_tl)
    EditText edtTenNongSan;
    @BindView(R.id.toolbar_dangtin_tl)
    Toolbar toolbar;
    //
    //
    public static String tennongsan="";
    public static String tg_batdau = "";
    public static String tg_ketthuc = "";
    public static double gia_thapnhat=0;
    public static double gia_caonhat=0;
    public static String noithumua="";
    public static String lienhe="";
    public static String hinhanh="";
    public static String mota="";
    public static int id_thuonglai=0;
    public static int id_loains=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_tin_tl);
        ButterKnife.bind(this);
        ActionToolBar();
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Đăng tin");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getData(){
        id_thuonglai = MainTLActivity.thuongLaiModel.getId();
        tennongsan = edtTenNongSan.getText().toString();
        tg_batdau = edtNgayBatDau.getText().toString();
        tg_ketthuc = edtNgayKetThuc.getText().toString();
        gia_thapnhat = Double.parseDouble(edtGiaThapNhat.getText().toString());
        gia_caonhat = Double.parseDouble(edtGiaCaoNhat.getText().toString());
        noithumua = edtNoiThuMua.getText().toString();
        lienhe = edtLienHe.getText().toString();
        mota = edtMoTa.getText().toString();
    }

    private void ThemThuMua(){
        getData();
        ThuonglaiService thuonglaiService = ApiClient.getClient().create(ThuonglaiService.class);
        Call<POST> call = thuonglaiService.themthumua(tennongsan, tg_batdau, tg_ketthuc, gia_thapnhat, gia_caonhat, noithumua,
                lienhe, hinhanh,mota, id_thuonglai, id_loains);
        call.enqueue(new Callback<POST>() {
            @Override
            public void onResponse(Call<POST> call, Response<POST> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Thêm thua mua thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<POST> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Thêm thua mua thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @OnClick(R.id.btn_luu_dangtin_tl)
    void ClickThem(){
        ThemThuMua();
    }

    @OnClick(R.id.hinhanh_dangtin_tl)
    void ChooseImage(View v){
        onPickPhoto(v);
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
                Glide.with(DangTinTLActivity.this).load(file).into(imgHinhAnh);
                hinhanh = String.valueOf(file);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
