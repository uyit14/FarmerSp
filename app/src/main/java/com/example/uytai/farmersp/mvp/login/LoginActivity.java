package com.example.uytai.farmersp.mvp.login;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uytai.farmersp.MainActivity;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.NongDanModel;
import com.example.uytai.farmersp.mvp.register.RegisterActivity;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements ILogin.View {

    TextInputLayout tipTaiKhoan, tipMatKhau;
    Button btnLogin;
    TextView tv_register;
    boolean loginstate = false;
    String taikhoan="", matkhau="";
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginPresenter loginPresenter = new LoginPresenter(this);
        loginPresenter.requestGetListUser();
        initView();
        getDataSignup();
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

    @Override
    public void getListUserSuccess(final List<NongDanModel> nongDanModels) {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taikhoan = tipTaiKhoan.getEditText().getText().toString().trim();
                matkhau = tipMatKhau.getEditText().getText().toString().trim();
                //
                if(!TextUtils.isEmpty(taikhoan) || !TextUtils.isEmpty(matkhau)){
                    for(int i = 0; i< nongDanModels.size(); i++){
                        if(taikhoan.equals(nongDanModels.get(i).getTaikhoan()) && matkhau.equals(nongDanModels.get(i).getMatkhau())){
                            loginstate = true;
                            position = i;
                            break;
                        }else{
                            loginstate = false;
                        }
                    }
                }else{
                    Log.d("uytai123", "Fill out");
                }
                if(loginstate){
                    NongDanModel nongDanModel = nongDanModels.get(position);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constant.KEY_PUT_OBJECT, nongDanModel);
                    intent.putExtra(Constant.KEY_PUT_BUNDLE, bundle);
                    startActivity(intent);
                    Log.d("uytai123", "Login Success");
                }else{
                    Log.d("uytai123", "Login Fail");
                }
            }
        });
    }

    @Override
    public void getListUserFail() {
        Toast.makeText(getApplicationContext(), "Load data Fail", Toast.LENGTH_SHORT).show();
    }
}
