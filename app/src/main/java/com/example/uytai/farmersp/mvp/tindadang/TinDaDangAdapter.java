package com.example.uytai.farmersp.mvp.tindadang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.NongSanModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class TinDaDangAdapter extends RecyclerView.Adapter<TinDaDangAdapter.TinDaDangViewHolder> {
    List<NongSanModel> arrNongsan;
    Context context;

    public TinDaDangAdapter(List<NongSanModel> arrNongsan, Context context) {
        this.arrNongsan = arrNongsan;
        this.context = context;
    }

    @Override
    public TinDaDangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_post, parent, false);
        return new TinDaDangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TinDaDangViewHolder holder, final int position) {
        holder.TenNongSan.setText(arrNongsan.get(position).getTenNongsan());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        holder.EndDate.setText(df.format(arrNongsan.get(position).getTgKetthuc()));
        holder.SDT.setText(arrNongsan.get(position).getSdtLienHe());
        holder.ImgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailTDDActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.KEY_PUT_OBJECT, arrNongsan.get(position));
                intent.putExtra(Constant.KEY_PUT_BUNDLE, bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrNongsan.size();
    }

    public class TinDaDangViewHolder extends RecyclerView.ViewHolder{
        TextView TenNongSan, EndDate, SDT;
        ImageView ImgEdit;
        public TinDaDangViewHolder(View itemView) {
            super(itemView);
            TenNongSan = itemView.findViewById(R.id.tenns_tindadang_tv);
            EndDate = itemView.findViewById(R.id.date_tindadang_tv);
            SDT = itemView.findViewById(R.id.sdt_tindadang_tv);
            ImgEdit = itemView.findViewById(R.id.edit_tindadang_img);
        }
    }
}
