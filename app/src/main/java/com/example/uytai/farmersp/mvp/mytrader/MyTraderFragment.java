package com.example.uytai.farmersp.mvp.mytrader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.model.DangKyModel;
import com.example.uytai.farmersp.model.ThuongLaiModel;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.NongDanService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyTraderFragment extends Fragment implements IMyTrader.View {

    @BindView(R.id.bar_post)
    Toolbar toolbar;
    MyTraderPresenter myTraderPresenter;
    ArrayList<Integer> arrIDTL;
    public MyTraderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_mytrader, container, false);
        ButterKnife.bind(this, root);
        ActionToolbar();
        myTraderPresenter = new MyTraderPresenter(this);
        myTraderPresenter.requestGetListIDTL();
        return root;
    }

    private void ActionToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Thương lái của tôi");
    }

    @Override
    public void getIDTLSuccess(List<DangKyModel> dangKyModels) {
        if(dangKyModels!=null){
            arrIDTL = new ArrayList<>();
            Log.d("uytai", dangKyModels.size()+"");
            for(int i = 0; i<dangKyModels.size() ; i++){
                arrIDTL.add(dangKyModels.get(i).getIdtl());
            }
            getListTrader(arrIDTL);
        }else{
            Toast.makeText(getContext(), "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getIDNDFail() {
        Toast.makeText(getContext(), "Lỗi trong khi tải, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
    }

    //
    public void getListTrader(ArrayList<Integer> arrIDTL){
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<List<ThuongLaiModel>> call = nongDanService.getThuongLaibyID(arrIDTL);
        call.enqueue(new Callback<List<ThuongLaiModel>>() {
            @Override
            public void onResponse(Call<List<ThuongLaiModel>> call, Response<List<ThuongLaiModel>> response) {
                if(response.isSuccessful()){
//                    Log.d("uytai123", "OK");
                }else{
//                    Log.d("uytai123", "NOT");
                }
            }

            @Override
            public void onFailure(Call<List<ThuongLaiModel>> call, Throwable t) {
//                Log.d("uytai123", "Fail");
            }
        });
    }
}
