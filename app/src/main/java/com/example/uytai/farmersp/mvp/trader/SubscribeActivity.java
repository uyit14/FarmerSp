package com.example.uytai.farmersp.mvp.trader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uytai.farmersp.MainActivity;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.DangKyModel;
import com.example.uytai.farmersp.model.ThuongLaiModel;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.NongDanService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class SubscribeActivity extends AppCompatActivity implements ISubscribe.View {
    @BindView(R.id.avatar)
    CircleImageView civ_avatar;
    @BindView(R.id.name)
    TextView tv_name;
    @BindView(R.id.rate)
    TextView tv_rate;
    @BindView(R.id.status)
    TextView tv_status;
    @BindView(R.id.btn_subcsribe)
    Button btn_subcsribe;
    @BindView(R.id.bar_subscribe)
    Toolbar toolbar;

    int idtl =0 ;
    SubscribePresenter subscribePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
        ButterKnife.bind(this);
        getTrader();
        ActionToolbar();
        subscribePresenter = new SubscribePresenter(this);
        subscribePresenter.requestGetSubscibed();
    }

    //
    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đăng ký");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //
    private void getTrader() {
        Bundle bundle = getIntent().getBundleExtra(Constant.KEY_PUT_OBJECT);
        if(bundle!=null){
            final ThuongLaiModel thuongLaiModel = (ThuongLaiModel) bundle.getSerializable(Constant.KEY_PUT_BUNDLE);
            idtl = thuongLaiModel.getId();
            //set Trader infor
            Glide.with(SubscribeActivity.this).load(thuongLaiModel.getAvatar()).placeholder(R.drawable.no_image).into(civ_avatar);
            tv_name.setText(thuongLaiModel.getTen());
            tv_rate.setText(thuongLaiModel.getRate()+"");
            tv_status.setText(thuongLaiModel.getStatus());
            //click button subcsribe
            btn_subcsribe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    requestSubscibe(MainActivity.nongDanModel.getId(), thuongLaiModel.getId());
                }
            });
        }
    }

    public void requestSubscibe(int idnd, int idtl) {
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        nongDanService.dangky(idnd,idtl).enqueue(new Callback<POST>() {
            @Override
            public void onResponse(Call<POST> call, Response<POST> response) {
                Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<POST> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void CheckSubscribedSuccess(List<DangKyModel> dangKyModels) {
        for(int i =0 ; i<dangKyModels.size() ; i++){
            if(dangKyModels.get(i).getIdnd()==MainActivity.nongDanModel.getId() && dangKyModels.get(i).getIdtl()==idtl){
                btn_subcsribe.setText("Đã đăng ký");
                btn_subcsribe.setEnabled(false);
            }
        }
    }

    @Override
    public void CheckSubscribedFail() {
        Toast.makeText(getApplicationContext(), "Có lỗi trong quá trình tải, vui lòng thử lại", Toast.LENGTH_SHORT).show();
    }
}
