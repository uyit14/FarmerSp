package com.example.uytai.farmersp.admin;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.NongDanModel;
import com.example.uytai.farmersp.retrofit.AdminService;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.NongDanService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;


public class NongDanFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.listviewNongDan)
    ListView listView;
    USNongDanAdapter usNongDanAdapter;
    ProgressDialog pDialog;
    List<NongDanModel> nongDanModels;
    @BindView(R.id.swiprefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    public NongDanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_nong_dan, container, false);
        ButterKnife.bind(this, root);
        nongDanModels = new ArrayList<>();
        pDialog = new ProgressDialog(getActivity());
        swipeRefreshLayout.setOnRefreshListener(this);
        requestgetlistNongDan();
        longClick();
        oneClick();
        return root;
    }

    private void oneClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ContentNDActivity.class);
                intent.putExtra(Constant.KEY_PUT_ID_ND, i+1);
                intent.putExtra(Constant.KEY_PUT_NAME, nongDanModels.get(i).getTen());
                startActivity(intent);

            }
        });
    }

    private void longClick(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                new AlertDialog.Builder(getActivity())
                        .setMessage("Bạn có chắn chắn xóa?")
                        .setCancelable(false)
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteuser(i+1);
                            }
                        })
                        .setNegativeButton("Không", null)
                     .show();
                return false;
            }
        });
    }

    private void deleteuser(int id){
        AdminService adminService = ApiClient.getClient().create(AdminService.class);
        Call<POST> call = adminService.deletend(id);
        call.enqueue(new Callback<POST>() {
            @Override
            public void onResponse(Call<POST> call, Response<POST> response) {
                Toast.makeText(getActivity().getApplicationContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<POST> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //get data nongdan
    private void requestgetlistNongDan(){
        pDialog.setMessage("Đang tải thông tin...!");
        pDialog.setCancelable(false);
        pDialog.show();
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<List<NongDanModel>> call = nongDanService.getUserND();
        call.enqueue(new Callback<List<NongDanModel>>() {
            @Override
            public void onResponse(Call<List<NongDanModel>> call, Response<List<NongDanModel>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        nongDanModels.clear();
                        nongDanModels.addAll(response.body());
                        if(nongDanModels.size()>0){
                            usNongDanAdapter = new USNongDanAdapter(getActivity(), nongDanModels);
                            listView.setAdapter(usNongDanAdapter);
                            usNongDanAdapter.notifyDataSetChanged();
                        }
                        if(pDialog.isShowing())
                            pDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<NongDanModel>> call, Throwable t) {
                if(pDialog.isShowing())
                    pDialog.dismiss();
                Toast.makeText(getActivity().getApplicationContext(), "Tai du lieu that bai", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                requestgetlistNongDan();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1500);
    }
}
