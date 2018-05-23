package com.example.uytai.farmersp.mvp.trader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.TraderModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by uytai on 4/13/2018.
 */

public class TraderAdapter extends RecyclerView.Adapter<TraderAdapter.TraderViewHolder> {
    List<TraderModel> arrTrader;
    Context context;

    public TraderAdapter(List<TraderModel> arrTrader, Context context) {
        this.arrTrader = arrTrader;
        this.context = context;
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
        List<TraderModel> traderModels = new ArrayList<>();
        Context context;
        public TraderViewHolder(final View itemView, Context context, List<TraderModel> traderModels) {
            super(itemView);
            this.traderModels = traderModels;
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
            TraderModel traderModel = this.traderModels.get(position);
            Intent intent = new Intent(context, SubscribeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constant.KEY_PUT_BUNDLE, traderModel);
            intent.putExtra(Constant.KEY_PUT_OBJECT, bundle);
            context.startActivity(intent);
        }
    }
}
