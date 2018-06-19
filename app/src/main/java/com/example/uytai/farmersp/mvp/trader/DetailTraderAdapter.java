package com.example.uytai.farmersp.mvp.trader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.model.ThuMuaModelTL;
import com.example.uytai.farmersp.thuonglai.TinDangTL.TinDaDangAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by uytai on 6/18/2018.
 */

public class DetailTraderAdapter extends BaseAdapter {
    Context context;
    List<ThuMuaModelTL> arrthuMuaModelTLS;

    public DetailTraderAdapter(Context context, List<ThuMuaModelTL> arrthuMuaModelTLS) {
        this.context = context;
        this.arrthuMuaModelTLS = arrthuMuaModelTLS;
    }


    @Override
    public int getCount() {
        return arrthuMuaModelTLS.size();
    }

    @Override
    public Object getItem(int i) {
        return arrthuMuaModelTLS.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView tenns, noithumua, ngaybatdau, sdt;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DetailTraderAdapter.ViewHolder viewHolder = null;
        if(view==null){
            viewHolder = new DetailTraderAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_detail_trader, null);
            viewHolder.tenns = view.findViewById(R.id.tenns_detail_trader);
            viewHolder.noithumua = view.findViewById(R.id.noithumua_detail_trader);
            viewHolder.ngaybatdau = view.findViewById(R.id.date_detail_trader);
            viewHolder.sdt = view.findViewById(R.id.sdt_detail_trader);
        }else{
            viewHolder = (DetailTraderAdapter.ViewHolder) view.getTag();
        }
        ThuMuaModelTL thuMuaModelTL = (ThuMuaModelTL) getItem(i);
        viewHolder.tenns.setText(thuMuaModelTL.getTenNongsan());
        viewHolder.noithumua.setText(thuMuaModelTL.getNoithumua());
        viewHolder.sdt.setText(thuMuaModelTL.getLienhe());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        viewHolder.ngaybatdau.setText(df.format(thuMuaModelTL.getTgBatdau()));
        return view;
    }
}
