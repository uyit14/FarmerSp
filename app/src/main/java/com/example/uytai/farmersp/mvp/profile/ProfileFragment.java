package com.example.uytai.farmersp.mvp.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uytai.farmersp.MainActivity;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.model.NongDanModel;
import com.example.uytai.farmersp.mvp.dangtin.DangTinActivity;
import com.example.uytai.farmersp.mvp.tindadang.TinDaDangActivity;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.NongDanService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;


public class ProfileFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.name)
    EditText edt_name;
    @BindView(R.id.phone)
    EditText edt_phone;
    @BindView(R.id.avatar)
    CircleImageView cir_avatar;
    @BindView(R.id.status)
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

    @BindView(R.id.countTDD)
    TextView tvcountTDD;

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
        tv_edit.setText("Lưu");
    }
    private void Disible() {
        edt_name.setEnabled(false);
        edt_phone.setEnabled(false);
        edt_status.setEnabled(false);
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
        if(TinDaDangActivity.countTDD==0){
            tvcountTDD.setText("0");
        }else{
            tvcountTDD.setText(TinDaDangActivity.countTDD+"");
        }
    }

    private void editInforUser(){
        String ten = edt_name.getText().toString();
        String sdt = edt_phone.getText().toString();
        String status = edt_status.getText().toString();
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<POST> call = nongDanService.editProfile(MainActivity.nongDanModel.getId(), ten, sdt, status);
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
                Toast.makeText(getContext(), "Có lỗi xảy ra, vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(getContext(), DangTinActivity.class));
    }
    @OnClick(R.id.img_tindadang)
    void TinDaDang(){
        startActivity(new Intent(getContext(), TinDaDangActivity.class));
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
}
