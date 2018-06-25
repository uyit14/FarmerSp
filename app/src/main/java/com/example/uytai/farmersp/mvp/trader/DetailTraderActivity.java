package com.example.uytai.farmersp.mvp.trader;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
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
import com.example.uytai.farmersp.thuonglai.TinDangTL.DetailTLActivity;
import com.example.uytai.farmersp.thuonglai.TinDangTL.TinDaDangAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTraderActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.bar_detail_trader)
    Toolbar toolbar;
    @BindView(R.id.listviewDetailTrader)
    ListView listView;
    DetailTraderAdapter detailTraderAdapter;
    List<ThuMuaModelTL> thuMuaModelTLS;
    @BindView(R.id.swip)
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog pDialog;

    //
    int idtl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trader);
        ButterKnife.bind(this);
        pDialog = new ProgressDialog(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        //
        idtl = getIntent().getIntExtra(Constant.KEY_PUT_ID_TL, -1);
        //
        requestGetListDetailTrader();
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
                intent.putExtra("ISND", 100);
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
    //
    private void requestGetListDetailTrader(){
        pDialog.setMessage("Đang xử lý...!");
        pDialog.setCancelable(false);
        pDialog.show();
        ThuonglaiService thuonglaiService = ApiClient.getClient().create(ThuonglaiService.class);
        Call<List<ThuMuaModelTL>> call = thuonglaiService.getThuMuabyIDTL(idtl);
        call.enqueue(new Callback<List<ThuMuaModelTL>>() {
            @Override
            public void onResponse(Call<List<ThuMuaModelTL>> call, Response<List<ThuMuaModelTL>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        thuMuaModelTLS.addAll(response.body());
                        detailTraderAdapter = new DetailTraderAdapter(getApplicationContext(), response.body());
                        listView.setAdapter(detailTraderAdapter);
                        if(pDialog.isShowing())
                            pDialog.dismiss();
                    }else {
                        Toast.makeText(getApplicationContext(), "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();
                        if(pDialog.isShowing())
                            pDialog.dismiss();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                    if(pDialog.isShowing())
                        pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<ThuMuaModelTL>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                if(pDialog.isShowing())
                    pDialog.dismiss();
            }
        });
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                requestGetListDetailTrader();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
