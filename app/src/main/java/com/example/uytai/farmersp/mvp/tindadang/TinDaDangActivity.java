package com.example.uytai.farmersp.mvp.tindadang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.model.NongSanModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TinDaDangActivity extends AppCompatActivity implements ITinDaDang.View {

    @BindView(R.id.recyclerviewTinDaDang)
    RecyclerView recyclerView;

    @BindView(R.id.bar_tindadang)
    Toolbar toolbar;

    TinDaDangAdapter tinDaDangAdapter;

    public static int countTDD = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_da_dang);
        ButterKnife.bind(this);
        //
        TinDaDangPresenter tinDaDangPresenter = new TinDaDangPresenter(this);
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
            tinDaDangAdapter = new TinDaDangAdapter(nongSanModels, getApplicationContext());
            recyclerView.setAdapter(tinDaDangAdapter);
        }
    }

    @Override
    public void getListNongSanFail() {
        Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
    }
}
