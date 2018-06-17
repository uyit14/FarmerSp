package com.example.uytai.farmersp.mvp.login;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uytai.farmersp.MainActivity;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.NongDanModel;
import com.example.uytai.farmersp.model.ThuongLaiModel;
import com.example.uytai.farmersp.mvp.register.RegisterActivity;
import com.example.uytai.farmersp.thuonglai.MainTLActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILogin.View {

    @BindView(R.id.login_nongdan)
    CheckBox cbNongdan;
    @BindView(R.id.login_thuonglai)
    CheckBox cbThuonglai;
    TextInputLayout tipTaiKhoan, tipMatKhau;
    Button btnLogin;
    TextView tv_register;
    boolean loginstate = false;
    String taikhoan="", matkhau="";
    int position;

    List<NongDanModel> listNongDan;
    List<ThuongLaiModel> listThuongLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        listNongDan = new ArrayList<>();
        listThuongLai = new ArrayList<>();
        LoginPresenter loginPresenter = new LoginPresenter(this);
        loginPresenter.requestGetListUser();
        loginPresenter.requestGetThuongLai();
        initView();
        getDataSignup();
        setChecked();
    }

    @OnClick(R.id.btn_login)
    void login(){
        signin();
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

    private void getDataSignup() {
        if(getIntent().getStringExtra(Constant.KEY_PUT_USERNAME)!=null && getIntent().getStringExtra(Constant.KEY_PUT_PASSWORD)!=null){
            String username = getIntent().getStringExtra(Constant.KEY_PUT_USERNAME);
            String password = getIntent().getStringExtra(Constant.KEY_PUT_PASSWORD);
            tipTaiKhoan.getEditText().setText(username);
            tipMatKhau.getEditText().setText(password);
        }
    }

    private void initView() {
        tipTaiKhoan = findViewById(R.id.login_username);
        tipMatKhau = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.tv_register);

        //
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void signin(){
        taikhoan = tipTaiKhoan.getEditText().getText().toString().trim();
        matkhau = tipMatKhau.getEditText().getText().toString().trim();
        //
        if(!TextUtils.isEmpty(taikhoan) || !TextUtils.isEmpty(matkhau)){
            if(cbNongdan.isChecked()){
                for(int i = 0; i< listNongDan.size(); i++){
                    if(taikhoan.equals(listNongDan.get(i).getTaikhoan()) && matkhau.equals(listNongDan.get(i).getMatkhau())){
                        loginstate = true;
                        position = i;
                        break;
                    }else{
                        loginstate = false;
                    }
                }
                //
                if(loginstate){
                    NongDanModel nongDanModel = listNongDan.get(position);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constant.KEY_PUT_OBJECT, nongDanModel);
                    intent.putExtra(Constant.KEY_PUT_BUNDLE, bundle);
                    startActivity(intent);
//                    Log.d("uytai123", "Login Success");
                }else{
                    Toast.makeText(getApplicationContext(), "Đăng nhập nông dân thất bại", Toast.LENGTH_SHORT).show();
                }
            }else if(cbThuonglai.isChecked()){
                for(int i = 0; i< listThuongLai.size(); i++){
                    if(taikhoan.equals(listThuongLai.get(i).getTaikhoan()) && matkhau.equals(listThuongLai.get(i).getMatkhau())){
                        loginstate = true;
                        position = i;
                        break;
                    }else{
                        loginstate = false;
                    }
                }
                //
                if(loginstate){
                    ThuongLaiModel thuongLaiModel = listThuongLai.get(position);
                    Intent intent = new Intent(getApplicationContext(), MainTLActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constant.KEY_PUT_OBJECT, thuongLaiModel);
                    intent.putExtra(Constant.KEY_PUT_BUNDLE, bundle);
                    startActivity(intent);
//                    Log.d("uytai123", "Login Success");
                }else{
                    Toast.makeText(getApplicationContext(), "Đăng nhập thương lái thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getListUserSuccess(final List<NongDanModel> nongDanModels) {
        listNongDan.addAll(nongDanModels);
    }

    @Override
    public void getListUserFail() {
//        Toast.makeText(getApplicationContext(), "Load data Fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getListTLSuccess(List<ThuongLaiModel> thuongLaiModels) {
        listThuongLai.addAll(thuongLaiModels);
    }

    @Override
    public void getListTLFail() {

    }
}
