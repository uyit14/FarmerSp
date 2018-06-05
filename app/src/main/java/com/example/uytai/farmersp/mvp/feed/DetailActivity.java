package com.example.uytai.farmersp.mvp.feed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.ThuMuaModel;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.img_buy)
    ImageView img_buy;
    @BindView(R.id.avatar)
    CircleImageView civ_avatar;
    @BindView(R.id.name)
    TextView tv_name;
    @BindView(R.id.date)
    TextView tv_date;
    @BindView(R.id.noithumua)
    TextView tv_noithumua;
    @BindView(R.id.lienhe)
    TextView tv_lienhe;
    @BindView(R.id.giathap)
    TextView tv_giathap;
    @BindView(R.id.giacao)
    TextView tv_giacao;
    @BindView(R.id.description)
    TextView tv_mota;
    @BindView(R.id.bar_detail)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
       getFeedDetail();
        ActionToolbar();
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


    private void getFeedDetail() {
        Bundle bundle = getIntent().getBundleExtra(Constant.KEY_PUT_OBJECT);
        if(bundle!=null){
            ThuMuaModel thuMuaModel = (ThuMuaModel) bundle.getSerializable(Constant.KEY_PUT_BUNDLE);
            Glide.with(DetailActivity.this).load(thuMuaModel.getAvatar()).placeholder(R.drawable.no_image).into(civ_avatar);
            Glide.with(DetailActivity.this).load(thuMuaModel.getHinhanh()).placeholder(R.drawable.no_image).into(img_buy);
            tv_name.setText(thuMuaModel.getTen());
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            tv_date.setText(df.format(thuMuaModel.getTgKetthuc()));
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            String giathapnhat = formatter.format(Double.parseDouble(thuMuaModel.getGiaThapnhat()));
            String giacaonhat =  formatter.format(Double.parseDouble(thuMuaModel.getGiaCaonhat()));
            tv_giathap.setText(giathapnhat + " VND");
            tv_giacao.setText(giacaonhat + " VND");
            tv_lienhe.setText(thuMuaModel.getLienhe());
            tv_noithumua.setText(thuMuaModel.getNoithumua());
            tv_mota.setText(thuMuaModel.getMota());
        }
    }
}
