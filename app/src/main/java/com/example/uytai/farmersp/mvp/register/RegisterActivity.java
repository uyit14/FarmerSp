package com.example.uytai.farmersp.mvp.register;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.mvp.login.LoginActivity;

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
    RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        registerPresenter = new RegisterPresenter(this);

        findViewById(R.id.signup_button).setOnClickListener(this);
        setChecked();
    }

    private void setChecked() {
        if(cbNongdan.isChecked()){
            cbThuonglai.setChecked(false);
        }else if(cbThuonglai.isChecked()){
            cbNongdan.setChecked(false);
        }
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
                registerPresenter.requestSignUp();
            }else if(cbThuonglai.isChecked()){
             registerPresenter.requestSignUpThuonglai();
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
        Toast.makeText(getApplicationContext(), "Đăng ký thất bại, vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signup_button:
                getDataSignUp();
        }
    }
}
