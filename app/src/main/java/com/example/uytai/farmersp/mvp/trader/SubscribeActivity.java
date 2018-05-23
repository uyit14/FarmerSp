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
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.TraderModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SubscribeActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
        ButterKnife.bind(this);
        getTrader();
        ActionToolbar();
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
            TraderModel traderModel = (TraderModel) bundle.getSerializable(Constant.KEY_PUT_BUNDLE);
            Log.d("uytai123", traderModel.getTen());

            //set Trader infor
            Glide.with(SubscribeActivity.this).load(traderModel.getAvatar()).placeholder(R.drawable.no_image).into(civ_avatar);
            tv_name.setText(traderModel.getTen());
            tv_rate.setText(traderModel.getRate()+"");
            tv_status.setText(traderModel.getStatus());
            //click button subcsribe
            btn_subcsribe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Subscribed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
