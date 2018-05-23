package com.example.uytai.farmersp.mvp.tindadang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.model.NongSan;

import java.util.List;

public class TinDaDangActivity extends AppCompatActivity implements ITinDaDang.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_da_dang);
        Log.d("uytai123", "nongsan_Oncreate");
        //
        TinDaDangPresenter tinDaDangPresenter = new TinDaDangPresenter(this);
        tinDaDangPresenter.requestGetListNongSan();
    }

    @Override
    public void getListNongSanSuccess(List<NongSan> nongSanModels) {
        if(nongSanModels!=null){
            Log.d("uytai123", "nongsan_not_null");
        }
    }

    @Override
    public void getListNongSanFail() {
        Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
    }
}
