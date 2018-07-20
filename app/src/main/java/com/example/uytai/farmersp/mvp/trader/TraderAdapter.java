package com.example.uytai.farmersp.mvp.trader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.ThuongLaiModel;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by uytai on 4/13/2018.
 */

public class TraderAdapter extends RecyclerView.Adapter<TraderAdapter.TraderViewHolder> {
    List<ThuongLaiModel> arrTrader;
    List<ThuongLaiModel> re_arrTrader;
    Context context;

    public TraderAdapter(List<ThuongLaiModel> arrTrader, Context context) {
        this.arrTrader = arrTrader;
        this.context = context;
        this.re_arrTrader = new ArrayList<ThuongLaiModel>();
        this.re_arrTrader.addAll(arrTrader);
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        arrTrader.clear();
        if(charText.length()==0){
            arrTrader.addAll(re_arrTrader);
        }else{
            for(ThuongLaiModel thuongLaiModel : re_arrTrader){
                removeAccent(thuongLaiModel.getTen());
                if(thuongLaiModel.getTen().toLowerCase(Locale.getDefault()).contains(charText)){
                    arrTrader.add(thuongLaiModel);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static String removeAccent(String s){
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public void RemoveItem(int position){
        arrTrader.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public TraderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_trader,parent, false);
        return new TraderViewHolder(view, context, arrTrader);
    }

    @Override
    public void onBindViewHolder(TraderViewHolder holder, int position) {
        Glide.with(context).load(arrTrader.get(position).getAvatar()).placeholder(R.drawable.no_image).into(holder.crImgAvatar);
        holder.tvName.setText(arrTrader.get(position).getTen());
        holder.tvRate.setText(arrTrader.get(position).getRate());
        holder.tvStatus.setText(arrTrader.get(position).getStatus());
    }


    @Override
    public int getItemCount() {
        return arrTrader.size();
    }

    //
    public static class TraderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CircleImageView crImgAvatar;
        TextView tvName, tvRate, tvStatus, tvReadmore;
        ImageView imgDetail;
        List<ThuongLaiModel> thuongLaiModels = new ArrayList<>();
        Context context;
        public TraderViewHolder(final View itemView, Context context, List<ThuongLaiModel> thuongLaiModels) {
            super(itemView);
            this.thuongLaiModels = thuongLaiModels;
            this.context = context;
            itemView.setOnClickListener(this);
            crImgAvatar = itemView.findViewById(R.id.img_avatar);
            tvName = itemView.findViewById(R.id.name);
            tvRate = itemView.findViewById(R.id.rate);
            tvStatus = itemView.findViewById(R.id.status);
            tvReadmore = itemView.findViewById(R.id.readmore);
            imgDetail = itemView.findViewById(R.id.detail);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ThuongLaiModel thuongLaiModel = this.thuongLaiModels.get(position);
            Intent intent = new Intent(context, SubscribeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constant.KEY_PUT_BUNDLE, thuongLaiModel);
            intent.putExtra(Constant.KEY_PUT_OBJECT, bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
