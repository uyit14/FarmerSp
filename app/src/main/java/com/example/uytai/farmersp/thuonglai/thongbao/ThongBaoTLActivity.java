package com.example.uytai.farmersp.thuonglai.thongbao;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

public class ThongBaoTLActivity extends AppCompatActivity {
    private float[] yData = {25f, 20f, 45f, 10f};
    private String[] xData = {"1","2","3","4"};
    PieChart pieChart;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao_tl);
        pieChart = (PieChart) findViewById(R.id.piechart);
        barChart = findViewById(R.id.barchart);
        setPieChart();
        setbarChart();
    }

    private void setbarChart() {
        barChart.getDescription().setEnabled(true);
        barChart.setExtraOffsets(5,10,5,5);
        barChart.setDragDecelerationFrictionCoef(0.9f);
        barChart.getDescription().setEnabled(true);
        barChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);
        ArrayList<BarEntry> yValues = new ArrayList<>();
        yValues.add(new BarEntry(5f,10));
        yValues.add(new BarEntry(4f,20));
        yValues.add(new BarEntry(3f,30));
        yValues.add(new BarEntry(2f,40));

        BarDataSet dataSet = new BarDataSet(yValues, "Desease Per Regions");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData barData = new BarData((dataSet));
        barData.setValueTextSize(10f);
        barData.setValueTextColor(Color.YELLOW);
        barChart.setData(barData);
    }

    private void setPieChart() {
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(true);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.9f);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);
        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(34f,"Ilala"));
        yValues.add(new PieEntry(56f,"Temeke"));
        yValues.add(new PieEntry(66f,"Kinondoni"));
        yValues.add(new PieEntry(45f,"Kigamboni"));

        PieDataSet dataSet = new PieDataSet(yValues, "Desease Per Regions");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData = new PieData((dataSet));
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.YELLOW);
        pieChart.setData(pieData);
        //PieChart Ends Here
    }
}
