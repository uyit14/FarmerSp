package com.example.uytai.farmersp.mvp.register;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.NongDanModel;
import com.example.uytai.farmersp.model.ThuongLaiModel;
import com.example.uytai.farmersp.mvp.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements IRegister.View, View.OnClickListener {

    @BindView(R.id.signup_name)
    TextInputLayout tipName;

    @BindView(R.id.signup_username)
    TextInputLayout tipUserName;

    @BindView(R.id.signup_password)
    TextInputLayout tipPassword;

    @BindView(R.id.signup_nongdan)
    CheckBox cbNongdan;

    @BindView(R.id.signup_thuonglai)
    CheckBox cbThuonglai;

    @BindView(R.id.signup_button)
    CardView signup_button;

    //
    public static  String ten="";
    public static  String avatar="";
    public static  String status="";
    public static  String sdt="";
    public static  String taikhoan="";
    public static  String matkhau="";
    public static double rate = 0.0;
    RegisterPresenter registerPresenter;
    //
    List<NongDanModel> listnongDanModels;
    List<ThuongLaiModel> listthuongLaiModels;

    //
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        registerPresenter = new RegisterPresenter(this);
        listnongDanModels = new ArrayList<>();
        listthuongLaiModels = new ArrayList<>();
        registerPresenter.requestGetNongDan();
        registerPresenter.requestGetThuongLai();
        findViewById(R.id.signup_button).setOnClickListener(this);
        setChecked();
    }

    private void setChecked() {
        //
        cbNongdan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbNongdan.isChecked()){
                    cbThuonglai.setChecked(false);
                }
            }
        });
        //
        cbThuonglai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cbThuonglai.isChecked()){
                    cbNongdan.setChecked(false);
                }
            }
        });
    }

    private void getDataSignUp() {
        //get data
        ten = tipName.getEditText().getText().toString().trim();
        taikhoan = tipUserName.getEditText().getText().toString().trim();
        matkhau = tipPassword.getEditText().getText().toString().trim();
        //sign up
        if(ten.equals("") || taikhoan.equals("") || matkhau.equals("")){
            Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }else{
            if(cbNongdan.isChecked()){
                for(int i=0; i<listnongDanModels.size();i++){
                    if(!taikhoan.equals(listnongDanModels.get(i).getTaikhoan())){
                        flag = true;
                    }else{
                        flag = false;
                    }
                }
                if(flag){
                    registerPresenter.requestSignUp();
                }else{
                    Toast.makeText(getApplicationContext(), "Tài khoản nông dân đã tồn tại, vui lòng chọn tài khoản khác!", Toast.LENGTH_SHORT).show();
                }
            }else if(cbThuonglai.isChecked()){
                for(int i=0; i<listthuongLaiModels.size();i++){
                    if(!taikhoan.equals(listthuongLaiModels.get(i).getTaikhoan())){
                        flag = true;
                    }else{
                        flag = false;
                    }
                }
                if(flag){
                    registerPresenter.requestSignUpThuonglai();
                }else{
                    Toast.makeText(getApplicationContext(), "Tài khoản thương lái đã tồn tại, vui lòng chọn tài khoản khác!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void SignUpSuccess() {
        Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.putExtra(Constant.KEY_PUT_USERNAME, taikhoan);
        intent.putExtra(Constant.KEY_PUT_PASSWORD, matkhau);
        startActivity(intent);
        finish();
    }

    @Override
    public void SignUpFail() {
        Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.putExtra(Constant.KEY_PUT_USERNAME, taikhoan);
        intent.putExtra(Constant.KEY_PUT_PASSWORD, matkhau);
        startActivity(intent);
        finish();
    }

    @Override
    public void getLisNongDanSuccess(List<NongDanModel> nongDanModels) {
        if(nongDanModels!=null){
            listnongDanModels.addAll(nongDanModels);
        }
    }

    @Override
    public void getListThuongLaiSuccess(List<ThuongLaiModel> thuongLaiModels) {
        if(thuongLaiModels!=null){
            listthuongLaiModels.addAll(thuongLaiModels);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signup_button:
                getDataSignUp();
        }
    }
}
