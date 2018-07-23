package com.example.uytai.farmersp.admin;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.ThuMuaModelTL;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.ThuonglaiService;
import com.example.uytai.farmersp.thuonglai.MainTLActivity;
import com.example.uytai.farmersp.thuonglai.TinDangTL.TinDaDangAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentTLActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    ListView listView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    List<ThuMuaModelTL> thuMuaModelTLS;
    ProgressDialog pDialog;
    ContentTLAdapter contentTLAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_tl);
        ButterKnife.bind(this);
        thuMuaModelTLS = new ArrayList<>();
        pDialog = new ProgressDialog(this);
        int ID = getIntent().getIntExtra(Constant.KEY_PUT_ID_ND, -1);
        String TEN = getIntent().getStringExtra(Constant.KEY_PUT_NAME);
        if(ID!=-1){
            requestGetBaiDangbyIDTL(ID);
        }
        if(TEN!=null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            toolbar.setTitle(TEN);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    private void requestGetBaiDangbyIDTL(int id) {
        pDialog.setMessage("Đang tải thông tin...!");
        pDialog.setCancelable(false);
        pDialog.show();
        ThuonglaiService thuonglaiService = ApiClient.getClient().create(ThuonglaiService.class);
        Call<List<ThuMuaModelTL>> call = thuonglaiService.getThuMuabyIDTL(id);
        call.enqueue(new Callback<List<ThuMuaModelTL>>() {
            @Override
            public void onResponse(Call<List<ThuMuaModelTL>> call, Response<List<ThuMuaModelTL>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        thuMuaModelTLS.addAll(response.body());
                        contentTLAdapter = new ContentTLAdapter(getApplicationContext(), response.body());
                        listView.setAdapter(contentTLAdapter);
                        contentTLAdapter.notifyDataSetChanged();
                        if(pDialog.isShowing())
                            pDialog.dismiss();
                    }else {
                        Toast.makeText(getApplicationContext(), "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();
                        if(pDialog.isShowing())
                            pDialog.dismiss();
                    }
                }else{
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
}
