package com.example.uytai.farmersp.mvp.mytrader;

import android.graphics.Color;
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
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyTraderFragment extends Fragment {

    @BindView(R.id.bar_thongke)
    Toolbar toolbar;
    @BindView(R.id.piechart)
    PieChart pieChart;
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
        setPieChart();
//        myTraderPresenter = new MyTraderPresenter(this, ro);
//        myTraderPresenter.requestGetListIDTL();
        return root;
    }

    private void ActionToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Thống kê");
    }

    private void setPieChart() {
        pieChart.setUsePercentValues(true);
        //pieChart.getDescription().setEnabled(true);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.9f);
        // pieChart.setTransparentCircleRadius(61f);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);
        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(1,"Bưởi"));
        yValues.add(new PieEntry(1,"Xoài"));
        yValues.add(new PieEntry(1,"Cam"));
        yValues.add(new PieEntry(1,"Ổi"));
        yValues.add(new PieEntry(4,"Khác"));

        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData = new PieData((dataSet));
        pieData.setValueTextSize(15f);
        pieData.setValueTextColor(Color.YELLOW);
        pieChart.setData(pieData);
        //PieChart Ends Here
    }

//    @Override
//    public void getIDTLSuccess(List<DangKyModel> dangKyModels) {
//        if(dangKyModels!=null){
//            arrIDTL = new ArrayList<>();
//            Log.d("uytai", dangKyModels.size()+"");
//            for(int i = 0; i<dangKyModels.size() ; i++){
//                arrIDTL.add(dangKyModels.get(i).getIdtl());
//            }
//            getListTrader(arrIDTL);
//        }else{
//            Toast.makeText(getContext(), "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();
//        }
//    }

//    @Override
//    public void getIDNDFail() {
//        Toast.makeText(getContext(), "Lỗi trong khi tải, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
//    }

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
