package com.example.uytai.farmersp.thuonglai.TinDangTL;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.ThuMuaModelTL;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.ThuonglaiService;
import com.example.uytai.farmersp.thuonglai.MainTLActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TinDaDangTLActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.float_tdd_tl)
    FloatingActionButton float_dangtin;
    @BindView(R.id.bar_tindadang_tl)
    Toolbar toolbar;
    @BindView(R.id.listviewTinDaDangTL)
    ListView listView;
    TinDaDangAdapter tinDaDangAdapter;
    List<ThuMuaModelTL> thuMuaModelTLS;
    @BindView(R.id.swip)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_dang_tl);
        ButterKnife.bind(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        requestGetListTinDaDang();
        ActionToolbar();
        ClickItem();
        thuMuaModelTLS = new ArrayList<>();
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tin đã đăng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void ClickItem() {
        //view detail
     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             Intent intent = new Intent(getApplicationContext(), DetailTLActivity.class);
             Bundle bundle = new Bundle();
             bundle.putSerializable(Constant.KEY_PUT_OBJECT, thuMuaModelTLS.get(i));
             intent.putExtra(Constant.KEY_PUT_BUNDLE, bundle);
             startActivity(intent);
         }
     });

     //delete
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });
    }

    //
    private void requestGetListTinDaDang(){
        ThuonglaiService thuonglaiService = ApiClient.getClient().create(ThuonglaiService.class);
        Call<List<ThuMuaModelTL>> call = thuonglaiService.getThuMuabyIDTL(MainTLActivity.thuongLaiModel.getId());
        call.enqueue(new Callback<List<ThuMuaModelTL>>() {
            @Override
            public void onResponse(Call<List<ThuMuaModelTL>> call, Response<List<ThuMuaModelTL>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        thuMuaModelTLS.addAll(response.body());
                        tinDaDangAdapter = new TinDaDangAdapter(getApplicationContext(), response.body());
                        listView.setAdapter(tinDaDangAdapter);
                    }else {
                        Toast.makeText(getApplicationContext(), "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();
                    }
                }else{

                }
            }

            @Override
            public void onFailure(Call<List<ThuMuaModelTL>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.float_tdd_tl)
    void DangTin(){
        Intent intent = new Intent(TinDaDangTLActivity.this, DangTinTLActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                requestGetListTinDaDang();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
