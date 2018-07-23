package com.example.uytai.farmersp.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.model.NongSanModel;

import java.util.List;

public class ContentNDAdapter extends BaseAdapter {
    Context context;
    List<NongSanModel> list;

    public ContentNDAdapter(Context context, List<NongSanModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView tvTenNS, tvSDT, tvDC;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_content_nd, null);
            viewHolder.tvTenNS = view.findViewById(R.id.tennongsan);
            viewHolder.tvSDT = view.findViewById(R.id.sdt);
            viewHolder.tvDC = view.findViewById(R.id.diachi);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        NongSanModel nongSanModel = (NongSanModel) getItem(i);
        viewHolder.tvTenNS.setText(nongSanModel.getTenNongsan());
        viewHolder.tvDC.setText(nongSanModel.getDiaChi());
        viewHolder.tvSDT.setText(nongSanModel.getSdtLienHe());
        return view;
    }
}
