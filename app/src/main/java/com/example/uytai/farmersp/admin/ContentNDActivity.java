package com.example.uytai.farmersp.admin;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.uytai.farmersp.MainActivity;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.NongSanModel;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.NongDanService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentNDActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    ListView listView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    List<NongSanModel> nongSanModels;
    ContentNDAdapter contentNDAdapter;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_nd);
        ButterKnife.bind(this);
        nongSanModels = new ArrayList<>();
        pDialog = new ProgressDialog(this);
        int ID = getIntent().getIntExtra(Constant.KEY_PUT_ID_ND, -1);
        String TEN = getIntent().getStringExtra(Constant.KEY_PUT_NAME);
        if(ID!=-1){
            requestGetBaiDangbyIDND(ID);
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


    private void requestGetBaiDangbyIDND(int idnd){
        pDialog.setMessage("Đang tải thông tin...!");
        pDialog.setCancelable(false);
        pDialog.show();
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<List<NongSanModel>> call = nongDanService.getNongSanbyIDND(idnd);
        call.enqueue(new Callback<List<NongSanModel>>() {
            @Override
            public void onResponse(Call<List<NongSanModel>> call, Response<List<NongSanModel>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        nongSanModels.addAll(response.body());
                        contentNDAdapter = new ContentNDAdapter(ContentNDActivity.this, nongSanModels);
                        listView.setAdapter(contentNDAdapter);
                        contentNDAdapter.notifyDataSetChanged();
                        if(pDialog.isShowing())
                            pDialog.dismiss();
                    }else{
                        Log.d("uytai123", "respone_null");
                        if(pDialog.isShowing())
                            pDialog.dismiss();
                    }
                }else{
//                    Log.d("uytai123", "respone_null_" +MainActivity.nongDanModel.getId());
                    Log.d("uytai123", "respone_fail");
                    if(pDialog.isShowing())
                        pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<NongSanModel>> call, Throwable t) {
                if(pDialog.isShowing())
                    pDialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
