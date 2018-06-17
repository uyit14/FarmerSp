package com.example.uytai.farmersp.mvp.profile;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uytai.farmersp.MainActivity;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.NongDanModel;
import com.example.uytai.farmersp.mvp.dangtin.DangTinActivity;
import com.example.uytai.farmersp.mvp.tindadang.TinDaDangActivity;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.NongDanService;
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

import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.name_profile)
    EditText edt_name;
    @BindView(R.id.phone_profile)
    EditText edt_phone;
    @BindView(R.id.avatar)
    CircleImageView cir_avatar;
    @BindView(R.id.status_profile)
    EditText edt_status;
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
    @BindView(R.id.dangxuat_profile)
    Button btnSignout;


    public ProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, root);
        swipeRefreshLayout.setOnRefreshListener(this);
        setInforUser();
        ActionToolbar();
        return root;
    }

    private void Enable() {
        edt_name.setEnabled(true);
        edt_phone.setEnabled(true);
        edt_status.setEnabled(true);
        cir_avatar.setEnabled(true);
        tv_edit.setText("Lưu");
    }
    private void Disible() {
        edt_name.setEnabled(false);
        edt_phone.setEnabled(false);
        edt_status.setEnabled(false);
        cir_avatar.setEnabled(false);
        tv_edit.setText("Sửa");
    }
    private void refresh(){}

    private void ActionToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Cá nhân");
    }


    private void setInforUser() {
        edt_name.setText(MainActivity.nongDanModel.getTen().toString());
        edt_status.setText(MainActivity.nongDanModel.getStatus().toString());
        edt_phone.setText(MainActivity.nongDanModel.getSdt());
        Glide.with(getContext()).load(MainActivity.nongDanModel.getAvatar()).placeholder(R.drawable.no_image).into(cir_avatar);
    }

    private void editInforUser(){
        String ten = edt_name.getText().toString();
        String sdt = edt_phone.getText().toString();
        String status = edt_status.getText().toString();
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<POST> call = nongDanService.editProfile(MainActivity.nongDanModel.getId(), ten, status, sdt, hinhanh);
        call.enqueue(new Callback<POST>() {
            @Override
            public void onResponse(Call<POST> call, Response<POST> response) {
                if(response.isSuccessful()){
                    swipeRefreshLayout.setRefreshing(true);
                    Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }else{

                }
            }

            @Override
            public void onFailure(Call<POST> call, Throwable t) {
                Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.dangxuat_profile)
    void Signout(){

    }
    @OnClick(R.id.img_dangtin)
    void DangTin(){
        Intent intent = new Intent(getContext(), DangTinActivity.class);
        intent.putExtra(Constant.KEY_PUT_ID_ND, MainActivity.nongDanModel.getId());
        startActivity(intent);
    }
    @OnClick(R.id.img_tindadang)
    void TinDaDang(){
        startActivity(new Intent(getContext(), TinDaDangActivity.class));
    }

    @OnClick(R.id.avatar)
    void ChangeImage(View v){
        onPickPhoto(v);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
            CropImage.activity(imageUri).setAspectRatio(1, 1).start(getActivity());
        }
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                Uri resultUri = result.getUri();
                File file = new File(resultUri.getPath());
                Glide.with(ProfileFragment.this).load(file).into(cir_avatar);
                hinhanh = String.valueOf(file);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
