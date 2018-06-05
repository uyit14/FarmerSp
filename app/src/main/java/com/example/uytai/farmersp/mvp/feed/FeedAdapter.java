package com.example.uytai.farmersp.mvp.feed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.ThuMuaModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by uytai on 4/15/2018.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {
    List<ThuMuaModel> arrFeed;
    Context context;

    public FeedAdapter(List<ThuMuaModel> arrFeed, Context context) {
        this.arrFeed = arrFeed;
        this.context = context;
    }

    public void RemoveItem(int position){
        arrFeed.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_feed,parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, final int position) {
        Glide.with(context).load(arrFeed.get(position).getAvatar()).placeholder(R.drawable.no_image).into(holder.crImgAvatar);
        Glide.with(context).load(arrFeed.get(position).getHinhanh()).placeholder(R.drawable.no_image).into(holder.Img_Buy);
        holder.Tv_Name.setText(arrFeed.get(position).getTen());
        holder.Tv_Desciption.setText(arrFeed.get(position).getMota());
        holder.Tv_NoiThuMua.setText(arrFeed.get(position).getNoithumua());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        holder.TV_Date.setText(df.format(arrFeed.get(position).getTgKetthuc()));
        //
        holder.btn_Viewdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.KEY_PUT_BUNDLE, arrFeed.get(position));
                intent.putExtra(Constant.KEY_PUT_OBJECT, bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrFeed.size();
    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder{
        CircleImageView crImgAvatar;
        ImageView Img_Buy;
        TextView Tv_Name, Tv_Desciption, Tv_NoiThuMua, TV_Date;
        Button btn_Viewdetail;
        public FeedViewHolder(View itemView) {
            super(itemView);
            crImgAvatar = itemView.findViewById(R.id.img_avatar);
            Img_Buy = itemView.findViewById(R.id.img_buy);
            Tv_Name = itemView.findViewById(R.id.name);
            Tv_Desciption = itemView.findViewById(R.id.description);
            Tv_NoiThuMua = itemView.findViewById(R.id.noithumua);
            TV_Date = itemView.findViewById(R.id.date);
            btn_Viewdetail = itemView.findViewById(R.id.btn_viewdetail);
        }
    }
}
