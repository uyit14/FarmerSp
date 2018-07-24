package com.example.uytai.farmersp.mvp.feed;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.uytai.farmersp.MainActivity;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.Rating;
import com.example.uytai.farmersp.model.ThuMuaModel;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.NongDanService;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

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
    @BindView(R.id.sdt_detail)
    TextView tv_sdt;
    @BindView(R.id.bar_detail)
    Toolbar toolbar;
    @BindView(R.id.ratingbar)
    RatingBar ratingBar;
    @BindView(R.id.clickrating)
    ImageView clickRating;
    ProgressDialog pDialog;
    NongDanService nongDanService;

    List<Rating> ratings = new ArrayList<>();

    int idbd = 0;
    int idtl = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        pDialog = new ProgressDialog(this);
        requestGetListRating();
        getFeedDetail();
        ActionToolbar();
        RatingClick();
    }



    //get rating theo id cuar tung bai dang
    private void requestGetRatingbyIDBD(int idbd){
        pDialog.setMessage("Đang tải thông tin...!");
        pDialog.setCancelable(false);
        pDialog.show();
        nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<List<Rating>> call = nongDanService.getratingbd(idbd);
        call.enqueue(new Callback<List<Rating>>() {
            @Override
            public void onResponse(Call<List<Rating>> cal, Response<List<Rating>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        //ratings.addAll(response.body());
                        handlerRating(response.body());
                    }
                    if(pDialog.isShowing())
                        pDialog.dismiss();
                }else{
                    //Log.d("uytai123", "NOT");
                }
            }

            @Override
            public void onFailure(Call<List<Rating>> call, Throwable t) {
               // Log.d("uytai123", "Fail");
                if(pDialog.isShowing())
                    pDialog.dismiss();
            }
        });
    }

    //xu ly tinh trung binh cua moi bai dang
    private void handlerRating(List<Rating> ratingList){
        float sum = 0;
        float rate = 0;
        for(int i=0 ; i<ratingList.size(); i++){
            sum+=ratingList.get(i).getRate();
        }
        rate = (sum/ratingList.size());
        rate = Math.round(rate);
        ratingBar.setRating(rate);
    }


    //click rating
    private void RatingClick() {
        clickRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pDialog.setMessage("Đang xử lý...!");
                pDialog.setCancelable(false);
                pDialog.show();
                float rating = ratingBar.getRating();
                boolean flag = false;
                if(idbd!=0 && idtl!=0 && MainActivity.nongDanModel.getId()!=0){
                    for(int i=0;i<ratings.size();i++){
                        if(ratings.get(i).getIdbd()==idbd && ratings.get(i).getId_user_rt()==MainActivity.nongDanModel.getId()){
                            flag=true;
                            break;
                        }
                    }
                    if(flag){
                        //update
                        //Log.d("uytai123", "update");
                        if(pDialog.isShowing())
                            pDialog.dismiss();
                        UpdateRating(idbd, MainActivity.nongDanModel.getId(), rating);
                    }else{
                        if(pDialog.isShowing())
                            pDialog.dismiss();
                        //Log.d("uytai", "insert");
                        //insert
                        Rating(idbd, MainActivity.nongDanModel.getId(), rating, idtl);
                    }
                }
            }
        });
    }


    //get allLis rating
    private void requestGetListRating(){
        nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<List<Rating>> call = nongDanService.getRating();
        call.enqueue(new Callback<List<Rating>>() {
            @Override
            public void onResponse(Call<List<Rating>> call, Response<List<Rating>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        ratings.addAll(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Rating>> call, Throwable t) {

            }
        });
    }


    //neu user chua rating thi insert
    private void Rating(int idbd, int id_user_rating, float rate, int iduser){
        pDialog.setMessage("Đang xử lý...!");
        pDialog.setCancelable(false);
        pDialog.show();
        nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<POST> call = nongDanService.rating(idbd, id_user_rating, rate, iduser);
        call.enqueue(new Callback<POST>() {
            @Override
            public void onResponse(Call<POST> call, Response<POST> response) {
                if(pDialog.isShowing())
                    pDialog.dismiss();
                //Log.d("uytai", "OK");
            }

            @Override
            public void onFailure(Call<POST> call, Throwable t) {
                if(pDialog.isShowing())
                    pDialog.dismiss();
                //Log.d("uytai", "Fail");
            }
        });
        //reload list rating
        requestGetRatingbyIDBD(idbd);
        requestGetListRating();
    }

    //neu user darating roi thi update rate
    private void UpdateRating(int idbd, int id_user_rt, float rate){
        pDialog.setMessage("Đang xử lý...!");
        pDialog.setCancelable(false);
        pDialog.show();
        nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<POST> call = nongDanService.updaterating(idbd, id_user_rt, rate);
        call.enqueue(new Callback<POST>() {
            @Override
            public void onResponse(Call<POST> call, Response<POST> response) {
                if(pDialog.isShowing())
                    pDialog.dismiss();
                //Log.d("uytai", "OK");
            }

            @Override
            public void onFailure(Call<POST> call, Throwable t) {
                if(pDialog.isShowing())
                    pDialog.dismiss();
                //Log.d("uytai", "Fail");
            }
        });
        //reload list rating
        requestGetRatingbyIDBD(idbd);
        requestGetListRating();
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
        Bundle bundle = getIntent().getBundleExtra(Constant.KEY_PUT_BUNDLE);
        if(bundle!=null){
            ThuMuaModel thuMuaModel = (ThuMuaModel) bundle.getSerializable(Constant.KEY_PUT_OBJECT);
            idbd = thuMuaModel.getId();
            idtl = thuMuaModel.getId_thuonglai();
            requestGetRatingbyIDBD(idbd);
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

    //
    //
    @OnClick(R.id.sdt_detail)
    void callNow(){
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
