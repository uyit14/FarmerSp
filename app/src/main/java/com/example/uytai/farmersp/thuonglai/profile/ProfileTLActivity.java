package com.example.uytai.farmersp.thuonglai.profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.Rating;
import com.example.uytai.farmersp.mvp.profile.ProfileFragment;
import com.example.uytai.farmersp.mvp.tindadang.TinDaDangActivity;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.NongDanService;
import com.example.uytai.farmersp.retrofit.ThuonglaiService;
import com.example.uytai.farmersp.thuonglai.MainTLActivity;
import com.example.uytai.farmersp.thuonglai.TinDangTL.DangTinTLActivity;
import com.example.uytai.farmersp.thuonglai.TinDangTL.TinDaDangTLActivity;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class ProfileTLActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.name_profile)
    EditText edt_name;
    @BindView(R.id.mota_profile)
    EditText edt_status;
    @BindView(R.id.avatar)
    CircleImageView cir_avatar;
    @BindView(R.id.bar_profile)
    Toolbar toolbar;
    @BindView(R.id.img_dangtin)
    ImageView img_dangtin;
    @BindView(R.id.img_tindadang)
    ImageView img_tindadang;
    @BindView(R.id.edit_profile)
    TextView tv_edit;
    @BindView(R.id.swip_editprofile)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.luotdanhgia)
    TextView luotdanhgia;

    @BindView(R.id.rate)
    TextView tv_rate;

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_tl);
        ButterKnife.bind(this);
        pDialog = new ProgressDialog(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        requestGetRating(MainTLActivity.thuongLaiModel.getId());
        ActionToolbar();
        setInforUser();
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

    //
    private void Enable() {
        edt_name.setEnabled(true);
        edt_status.setEnabled(true);
        cir_avatar.setEnabled(true);
        tv_edit.setText("Lưu");
    }
    private void Disible() {
        edt_name.setEnabled(false);
        edt_status.setEnabled(false);
        cir_avatar.setEnabled(false);
        tv_edit.setText("Sửa");
    }

    private void setInforUser() {
        tv_rate.setText(MainTLActivity.thuongLaiModel.getRate());
        edt_name.setText(MainTLActivity.thuongLaiModel.getTen().toString());
        edt_status.setText(MainTLActivity.thuongLaiModel.getStatus().toString());
        Glide.with(getApplicationContext()).load(MainTLActivity.thuongLaiModel.getAvatar()).placeholder(R.drawable.no_image).into(cir_avatar);
    }

    //
    private void requestGetRating(int idtl){
        pDialog.setMessage("Đang tải thông tin...!");
        pDialog.setCancelable(false);
        pDialog.show();
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<List<Rating>> call = nongDanService.getratingtl(idtl);
        call.enqueue(new Callback<List<Rating>>() {
            @Override
            public void onResponse(Call<List<Rating>> cal, Response<List<Rating>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        //ratings.addAll(response.body());
                        handlerRating(response.body());
                        Log.d("uytai123", response.body().size()+"");
                    }
                    if(pDialog.isShowing())
                        pDialog.dismiss();
                }else{
                    Log.d("uytai123", "NOT");
                    if(pDialog.isShowing())
                        pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Rating>> call, Throwable t) {
                Log.d("uytai123", "Fail");
                if(pDialog.isShowing())
                    pDialog.dismiss();
            }
        });
    }

    //
    //xu ly tinh trung binh cua moi bai dang
    private void handlerRating(List<Rating> ratingList){
        float sum = 0;
        float rate = 0;
        for(int i=0 ; i<ratingList.size(); i++){
            sum+=ratingList.get(i).getRate();
        }
        if(ratingList.size()>0){
            rate = (sum/ratingList.size());
        }
        //rate = Math.round(rate);
        tv_rate.setText(rate+"");
        luotdanhgia.setText(ratingList.size()+"");
    }

    private void editInforUser(){
        pDialog.setMessage("Đang xử lý...!");
        pDialog.setCancelable(false);
        pDialog.show();
        String ten = edt_name.getText().toString();
        String status = edt_status.getText().toString();
        ThuonglaiService thuonglaiService = ApiClient.getClient().create(ThuonglaiService.class);
        Call<POST> call = thuonglaiService.editProfileTL(MainTLActivity.thuongLaiModel.getId(), ten, status, hinhanh);
        call.enqueue(new Callback<POST>() {
            @Override
            public void onResponse(Call<POST> call, Response<POST> response) {
                if(response.isSuccessful()){
                    swipeRefreshLayout.setRefreshing(true);
                    Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    if(pDialog.isShowing())
                        pDialog.dismiss();
                }else{

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
    }


    @OnClick(R.id.edit_profile)
    void Edit(){
        switch (tv_edit.getText().toString()){
            case "Sửa":
                Enable();
                break;
            case "Lưu":
                editInforUser();
                break;
            default:
        }
    }

    @OnClick(R.id.img_dangtin)
    void DangTin(){
        Intent intent = new Intent(getApplicationContext(), DangTinTLActivity.class);
        intent.putExtra(Constant.KEY_PUT_ID_ND, MainTLActivity.thuongLaiModel.getId());
        startActivity(intent);
    }
    @OnClick(R.id.img_tindadang)
    void TinDaDang(){
        startActivity(new Intent(getApplicationContext(), TinDaDangTLActivity.class));
    }

    //
    @OnClick(R.id.avatar)
    void ChangeImage(View v){
        onPickPhoto(v);
    }



    //------------- chon anh--------------//
    private static final int GALLERY_PICK = 1;
    String hinhanh;
    public void onPickPhoto(View view){
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galleryIntent, "SELECT IMAGE"),GALLERY_PICK);
    }

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GALLERY_PICK && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            CropImage.activity(imageUri).setAspectRatio(1, 1).start(ProfileTLActivity.this);
        }
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                Uri resultUri = result.getUri();
                File file = new File(resultUri.getPath());
                Glide.with(ProfileTLActivity.this).load(file).into(cir_avatar);
                hinhanh = String.valueOf(file);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
