package com.example.uytai.farmersp.thuonglai.thongbao;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.uytai.farmersp.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThongBaoTLActivity extends AppCompatActivity {
    private float[] yData = {25f, 20f, 45f, 10f};
    private String[] xData = {"1","2","3","4"};
    PieChart pieChart, pieChart2;

    @BindView(R.id.thongke_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao_tl);
        ButterKnife.bind(this);
        pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart2 = findViewById(R.id.piechart2);
        setPieChart();
        setpieChart2();
        ActionToolbar();
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thống kê");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        yValues.add(new PieEntry(5f,"Ăn quả"));
        yValues.add(new PieEntry(2f,"Công nghiệp"));
        yValues.add(new PieEntry(1f,"Lương thực"));
        //yValues.add(new PieEntry(0f,"Khác"));

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

    //
    private void setpieChart2() {
        pieChart2.setUsePercentValues(true);
        pieChart2.getDescription().setEnabled(true);
        pieChart2.setExtraOffsets(5,10,5,5);
        pieChart2.setDragDecelerationFrictionCoef(0.9f);
       // pieChart2.setTransparentCircleRadius(61f);
        pieChart2.setHoleColor(Color.WHITE);
        pieChart2.animateY(1000, Easing.EasingOption.EaseInOutCubic);
        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(2f,"Tiền Giang"));
        yValues.add(new PieEntry(1f,"Đắk Lắk"));
        yValues.add(new PieEntry(3f,"Trà Vinh"));
        yValues.add(new PieEntry(1f,"Bến tre"));

        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData pieData = new PieData((dataSet));
        pieData.setValueTextSize(15f);
        pieData.setValueTextColor(Color.YELLOW);
        pieChart2.setData(pieData);
        //PieChart Ends Here
    }
}
