package com.example.uytai.farmersp.mvp.tindadang;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.model.NongSanModel;
import com.example.uytai.farmersp.mvp.dangtin.DangTinActivity;
import com.example.uytai.farmersp.mvp.feed.DetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TinDaDangActivity extends AppCompatActivity implements ITinDaDang.View {

    @BindView(R.id.recyclerviewTinDaDang)
    RecyclerView recyclerView;

    @BindView(R.id.bar_tindadang)
    Toolbar toolbar;

    @BindView(R.id.count_tdd)
    TextView tv_count;
    @BindView(R.id.float_tdd)
    FloatingActionButton btn_float;

    TinDaDangAdapter tinDaDangAdapter;
    TinDaDangPresenter tinDaDangPresenter;

    int countTDD = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_da_dang);
        ButterKnife.bind(this);
        //
        tinDaDangPresenter = new TinDaDangPresenter(this);
        tinDaDangPresenter.requestGetListNongSan();
        //
        ActionToolbar();
        initView();
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tin đã đăng");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void getListNongSanSuccess(List<NongSanModel> nongSanModels) {
        if(nongSanModels !=null){
            countTDD = nongSanModels.size();
            tv_count.setText(countTDD+"");
            tinDaDangAdapter = new TinDaDangAdapter(nongSanModels, getApplicationContext());
            recyclerView.setAdapter(tinDaDangAdapter);
        }
    }

    @Override
    public void getListNongSanFail() {
        Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.float_tdd)
    void DangTin(){
        startActivity(new Intent(getApplicationContext(), DangTinActivity.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
       // tinDaDangPresenter.requestGetListNongSan();
    }
}
