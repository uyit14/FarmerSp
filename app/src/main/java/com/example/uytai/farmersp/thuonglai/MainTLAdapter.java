package com.example.uytai.farmersp.thuonglai;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.NongSanModelTL;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by uytai on 6/15/2018.
 */

public class MainTLAdapter extends RecyclerView.Adapter<MainTLAdapter.FeedTLViewHolder> {
    List<NongSanModelTL> listNongSanTL;
    List<NongSanModelTL> re_listNongSanTL;
    Context context;
//
    public MainTLAdapter(Context context, List<NongSanModelTL> listNongSanTL) {
        this.listNongSanTL = listNongSanTL;
        this.context = context;
        this.re_listNongSanTL = new ArrayList<NongSanModelTL>();
        this.re_listNongSanTL.addAll(listNongSanTL);
    }
//
//    public void filter(String charText){
//        charText = charText.toLowerCase(Locale.getDefault());
//        listNongSanTL.clear();
//        if(charText.length()==0){
//            listNongSanTL.addAll(re_listNongSanTL);
//        }else{
//            for(NongSanModelTL nongSanModelTL : re_listNongSanTL){
//                removeAccent(nongSanModelTL.getTennongsan());
//                if(nongSanModelTL.getTennongsan().toLowerCase(Locale.getDefault()).contains(charText)){
//                    listNongSanTL.add(nongSanModelTL);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }
//
//    //bỏ dấu
//    public static String removeAccent(String s){
//        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
//        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
//        return pattern.matcher(temp).replaceAll("");
//    }
//
//    @Override
//    public int getCount() {
//        return listNongSanTL.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return listNongSanTL.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    public class ViewHolder{
//        CircleImageView avatar;
//        TextView tennongsan, ngaybatdau, ngayketthuc, mota;
//        ImageView hinhanh;
//        Button xemchitiet;
//    }
//
//    @Override
//    public View getView(final int position, View view, ViewGroup parent) {
//        ViewHolder viewHolder = null;
//        if(viewHolder==null){
//            viewHolder = new ViewHolder();
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.item_nongsan_tl, null);
//            viewHolder.avatar = view.findViewById(R.id.img_avatar_nd);
//            viewHolder.tennongsan = view.findViewById(R.id.tenns_ns);
//            viewHolder.ngaybatdau = view.findViewById(R.id.startdate_ns);
//            viewHolder.ngayketthuc = view.findViewById(R.id.enddate_ns);
//            viewHolder.mota = view.findViewById(R.id.description_ns);
//            viewHolder.hinhanh = view.findViewById(R.id.hinhanh_ns);
//            viewHolder.xemchitiet = view.findViewById(R.id.btn_viewdetail_ns);
//        }else{
//            viewHolder = (ViewHolder) view.getTag();
//        }
//        final NongSanModelTL nongSanModelTL = (NongSanModelTL) getItem(position);
//        viewHolder.tennongsan.setText(nongSanModelTL.getTennongsan());
//        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//        viewHolder.ngaybatdau.setText(df.format(nongSanModelTL.getTgBatdau()));
//        viewHolder.ngayketthuc.setText(df.format(nongSanModelTL.getTgKetthuc()));
//        viewHolder.mota.setText(nongSanModelTL.getMota());
//        Glide.with(context).load(nongSanModelTL.getAvatar()).placeholder(R.drawable.no_image).into(viewHolder.avatar);
//        Glide.with(context).load(nongSanModelTL.getHinhanh()).placeholder(R.drawable.no_image).into(viewHolder.hinhanh);
//        viewHolder.xemchitiet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, DetailNSActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(Constant.KEY_PUT_OBJECT, nongSanModelTL);
//                intent.putExtra(Constant.KEY_PUT_BUNDLE, bundle);
//                context.startActivity(intent);
//            }
//        });
//        return view;
//    }



    /////////////-------------------------//////////////////////

//    public void setfilter(ArrayList<ThuMuaModel> thuMuaModels){
//        re_arrFeed = new ArrayList<>();
//        re_arrFeed.addAll(thuMuaModels);
//        notifyDataSetChanged();
//    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        listNongSanTL.clear();
        if(charText.length()==0){
            listNongSanTL.addAll(re_listNongSanTL);
        }else{
            for(NongSanModelTL thuMuaModel : re_listNongSanTL){
                removeAccent(thuMuaModel.getTennongsan());
                if(thuMuaModel.getTennongsan().toLowerCase(Locale.getDefault()).contains(charText)){
                    listNongSanTL.add(thuMuaModel);
                }
            }
        }
        notifyDataSetChanged();
    }

    //bỏ dấu
    public static String removeAccent(String s){
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public void RemoveItem(int position){
        listNongSanTL.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public FeedTLViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_nongsan_tl,parent, false);
        return new FeedTLViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedTLViewHolder holder, final int position) {
        holder.tennongsan.setText(listNongSanTL.get(position).getTennongsan());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        holder.ngaybatdau.setText(df.format(listNongSanTL.get(position).getTgBatdau()));
        holder.ngayketthuc.setText(df.format(listNongSanTL.get(position).getTgKetthuc()));
        holder.mota.setText(listNongSanTL.get(position).getMota());
        Glide.with(context).load(listNongSanTL.get(position).getAvatar()).placeholder(R.drawable.no_image).into(holder.avatar);
        Glide.with(context).load(listNongSanTL.get(position).getHinhanh()).placeholder(R.drawable.no_image).into(holder.hinhanh);
        holder.xemchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailNSActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.KEY_PUT_OBJECT, listNongSanTL.get(position));
                intent.putExtra(Constant.KEY_PUT_BUNDLE, bundle);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listNongSanTL.size();
    }

    public static class FeedTLViewHolder extends RecyclerView.ViewHolder{
        CircleImageView avatar;
        TextView tennongsan, ngaybatdau, ngayketthuc, mota;
        ImageView hinhanh;
        Button xemchitiet;

        public FeedTLViewHolder(View view) {
            super(view);
            avatar = view.findViewById(R.id.img_avatar_nd);
            tennongsan = view.findViewById(R.id.tenns_ns);
            ngaybatdau = view.findViewById(R.id.startdate_ns);
            ngayketthuc = view.findViewById(R.id.enddate_ns);
            mota = view.findViewById(R.id.description_ns);
            hinhanh = view.findViewById(R.id.hinhanh_ns);
            xemchitiet = view.findViewById(R.id.btn_viewdetail_ns);
        }
    }
}
