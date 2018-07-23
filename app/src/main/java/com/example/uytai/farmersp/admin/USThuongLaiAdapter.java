package com.example.uytai.farmersp.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.model.NongDanModel;
import com.example.uytai.farmersp.model.ThuongLaiModel;

import java.util.List;

public class USThuongLaiAdapter extends BaseAdapter {
    Context context;
    List<ThuongLaiModel> list;

    public USThuongLaiAdapter(Context context, List<ThuongLaiModel> list) {
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
        TextView tvID, tvUsername, tvName;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view==null){
            viewHolder = new USThuongLaiAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_user, null);
            viewHolder.tvID = view.findViewById(R.id.id_user);
            viewHolder.tvUsername = view.findViewById(R.id.name_user);
            viewHolder.tvName = view.findViewById(R.id.name_show);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        ThuongLaiModel thuongLaiModel = (ThuongLaiModel) getItem(i);
        viewHolder.tvID.setText(thuongLaiModel.getId()+"");
        viewHolder.tvUsername.setText(thuongLaiModel.getTaikhoan());
        viewHolder.tvName.setText(thuongLaiModel.getTen());
        return view;
    }
}
