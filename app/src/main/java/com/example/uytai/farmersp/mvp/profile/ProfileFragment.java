package com.example.uytai.farmersp.mvp.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.uytai.farmersp.MainActivity;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.UserModel;
import com.example.uytai.farmersp.mvp.dangtin.DangTinActivity;
import com.example.uytai.farmersp.mvp.tindadang.TinDaDangActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {
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
        setInforUser();
        ActionToolbar();
        return root;
    }

    private void ActionToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Cá nhân");
    }


    private void setInforUser() {
        edt_name.setText(MainActivity.userModel.getTen().toString());
        edt_status.setText(MainActivity.userModel.getStatus().toString());
        edt_phone.setText(MainActivity.userModel.getSdt());
        Glide.with(getContext()).load(MainActivity.userModel.getAvatar()).placeholder(R.drawable.no_image).into(cir_avatar);
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
}
