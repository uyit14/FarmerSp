package com.example.uytai.farmersp.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.NongDanModel;
import com.example.uytai.farmersp.model.ThuongLaiModel;
import com.example.uytai.farmersp.retrofit.AdminService;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.NongDanService;
import com.example.uytai.farmersp.retrofit.ThuonglaiService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class ThuongLaiFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.listviewThuongLai)
    ListView listView;
    @BindView(R.id.swiprefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    List<ThuongLaiModel> thuongLaiModels;
    USThuongLaiAdapter usThuongLaiAdapter;

    public ThuongLaiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_thuong_lai, container, false);
        ButterKnife.bind(this, root);
        swipeRefreshLayout.setOnRefreshListener(this);
        thuongLaiModels = new ArrayList<>();
        requestgetlistThuonglai();
        longClick();
        return root;
    }

    private void requestgetlistThuonglai() {
        ThuonglaiService thuonglaiService = ApiClient.getClient().create(ThuonglaiService.class);
        Call<List<ThuongLaiModel>> call = thuonglaiService.getUserTL();
        call.enqueue(new Callback<List<ThuongLaiModel>>() {
            @Override
            public void onResponse(Call<List<ThuongLaiModel>> call, Response<List<ThuongLaiModel>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        thuongLaiModels.clear();
                        thuongLaiModels.addAll(response.body());
                        usThuongLaiAdapter = new USThuongLaiAdapter(getActivity(), thuongLaiModels);
                        listView.setAdapter(usThuongLaiAdapter);
                        usThuongLaiAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ThuongLaiModel>> call, Throwable t) {

            }
        });
    }

    private void longClick() {
        //
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ContentTLActivity.class);
                intent.putExtra(Constant.KEY_PUT_ID_ND, i+1);
                intent.putExtra(Constant.KEY_PUT_NAME, thuongLaiModels.get(i).getTen());
                startActivity(intent);

            }
        });
        //
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
        Call<POST> call = adminService.deletetl(id);
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

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                requestgetlistThuonglai();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1500);
    }
}
