package com.example.uytai.farmersp.mvp.trader;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.model.ThuongLaiModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TraderFragment extends Fragment implements ITrader.View, SearchView.OnQueryTextListener {

    @BindView(R.id.recyclerviewTrader)
    RecyclerView recyclerviewTrader;
    @BindView(R.id.bar_trader)
    Toolbar toolbar;
    @BindView(R.id.search_trader)
    SearchView searchView;
    TraderAdapter traderAdapter;

    ProgressDialog pDialog;

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
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pDialog = new ProgressDialog(getActivity());
        TraderPresenter traderPresenter = new TraderPresenter(this);
        traderPresenter.requestGetListTrader();
        searchView.setOnQueryTextListener(this);
        initView();
        ActionToolbar();
    }

    private void ActionToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Thương lái");
    }


    private void initView() {
        recyclerviewTrader.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerviewTrader.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void getListTraderSuccess(List<ThuongLaiModel> thuongLaiModel) {
        if(thuongLaiModel !=null){
            traderAdapter = new TraderAdapter(thuongLaiModel, getActivity().getApplicationContext());
            recyclerviewTrader.setAdapter(traderAdapter);
        }else{
            Toast.makeText(getActivity().getApplicationContext(), "Null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getListTraderFail() {
        Toast.makeText(getActivity().getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialogProgress() {
        pDialog.setMessage("Đang tải thông tin...!");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    public void dismissDialog() {
        if(pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        Log.d("uytai123", s+"");
        traderAdapter.filter(s);
        return true;
    }
}
