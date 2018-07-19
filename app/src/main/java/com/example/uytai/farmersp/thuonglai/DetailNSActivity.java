package com.example.uytai.farmersp.thuonglai;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.NongSanModelTL;
import com.example.uytai.farmersp.thuonglai.nongsan.MapActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailNSActivity extends AppCompatActivity {
    @BindView(R.id.img_avatar_nd)
    CircleImageView cir_avatar;
    @BindView(R.id.tenns_ns_detail)
    TextView tv_tenns;
    @BindView(R.id.startdate_ns_detail)
    TextView tv_startdate;
    @BindView(R.id.enddate_ns_detail)
    TextView tv_enddate;
    @BindView(R.id.name_detail)
    TextView tv_name;
    @BindView(R.id.sdt_detail)
    TextView tv_sdt;
    @BindView(R.id.diachi_detail)
    TextView tv_diachi;
    @BindView(R.id.description_detail)
    TextView tv_description;
    @BindView(R.id.hinhanh_detail)
    ImageView img_hinhanh;
    @BindView(R.id.bar_detail)
    Toolbar toolbar;

    NongSanModelTL nongSanModelTL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ns);
        ButterKnife.bind(this);
        //
        getDateDetail();
        setInfor();
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

    private void setInfor() {
        if (nongSanModelTL != null) {
            tv_description.setText(nongSanModelTL.getMota());
            tv_name.setText(nongSanModelTL.getTen());
            tv_diachi.setText(nongSanModelTL.getDiachi());
            tv_sdt.setText(nongSanModelTL.getSdtLh());
            tv_tenns.setText(nongSanModelTL.getTennongsan());
            Glide.with(getApplicationContext()).load(nongSanModelTL.getAvatar()).placeholder(R.drawable.no_image).into(cir_avatar);
            Glide.with(getApplicationContext()).load(nongSanModelTL.getHinhanh()).placeholder(R.drawable.no_image).into(img_hinhanh);
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            tv_startdate.setText(df.format(nongSanModelTL.getTgBatdau()));
            tv_enddate.setText(df.format(nongSanModelTL.getTgKetthuc()));
        }
    }

    private void getDateDetail() {
        Bundle bundle = getIntent().getBundleExtra(Constant.KEY_PUT_BUNDLE);
        if (bundle != null) {
            nongSanModelTL = (NongSanModelTL) bundle.getSerializable(Constant.KEY_PUT_OBJECT);
        }
    }

    @OnClick(R.id.layout_address)
    void GotoMap(){
        Intent intent = new Intent(DetailNSActivity.this, MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.KEY_PUT_OBJECT, nongSanModelTL);
        intent.putExtra(Constant.KEY_PUT_BUNDLE, bundle);
        startActivity(intent);
    }

    //
    @OnClick(R.id.sdt_detail)
    void callNow() {
        checkAndRequestPermissions();
        showDialogConfirm();
    }

    //
    //show dialog call or send mess
    private void showDialogConfirm() {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.contact_custom);
        ImageView btnCall = dialog.findViewById(R.id.btnCall);
        ImageView btnSendMessage = dialog.findViewById(R.id.btnMessage);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentCall();
            }
        });
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSendMesseage();
            }
        });
        dialog.show();
    }

    //
    //call
    private void intentCall() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + tv_sdt.getText().toString()));
        startActivity(intent);
    }

    //send mess
    private void intentSendMesseage() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + tv_sdt.getText().toString()));
        startActivity(intent);
    }

    //
    private void checkAndRequestPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS
        };
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
        }
    }
}
