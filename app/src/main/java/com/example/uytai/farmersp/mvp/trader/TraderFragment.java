package com.example.uytai.farmersp.mvp.trader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.TraderModel;
import com.example.uytai.farmersp.respone.TraderRespone;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TraderFragment extends Fragment implements ITrader.View {

    @BindView(R.id.recyclerviewTrader)
    RecyclerView recyclerviewTrader;
    @BindView(R.id.bar_trader)
    Toolbar toolbar;
    TraderAdapter traderAdapter;

    public TraderFragment() {
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
        View root = inflater.inflate(R.layout.fragment_trader, container, false);
        ButterKnife.bind(this, root);
        TraderPresenter traderPresenter = new TraderPresenter(this);
        traderPresenter.requestGetListTrader();
        initView();
        ActionToolbar();
        return root;
    }

    private void ActionToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Thương lái");
    }


    private void initView() {
        recyclerviewTrader.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerviewTrader.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void getListTraderSuccess(List<TraderModel> traderModel) {
        if(traderModel!=null){
            traderAdapter = new TraderAdapter(traderModel, getActivity().getApplicationContext());
            recyclerviewTrader.setAdapter(traderAdapter);
            Log.d("uytai123", traderModel.get(0).getTen());
        }else{
            Toast.makeText(getActivity().getApplicationContext(), "Null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getListTraderFail() {
        Toast.makeText(getActivity().getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
    }
}
