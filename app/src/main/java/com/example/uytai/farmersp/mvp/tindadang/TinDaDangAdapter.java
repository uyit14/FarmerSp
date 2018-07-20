package com.example.uytai.farmersp.mvp.tindadang;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uytai.farmersp.R;
import com.example.uytai.farmersp.config.Constant;
import com.example.uytai.farmersp.model.NongSanModel;
import com.example.uytai.farmersp.retrofit.ApiClient;
import com.example.uytai.farmersp.retrofit.NongDanService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

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
        holder.DiaChi.setText(arrNongsan.get(position).getDiaChi());
        holder.ImgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailTDDActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.KEY_PUT_OBJECT, arrNongsan.get(position));
                intent.putExtra(Constant.KEY_PUT_BUNDLE, bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        //
        holder.setItemLongClickListener(new ItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, int pos) {
//                showDialog(arrNongsan.get(position).getId(), arrNongsan.get(pos).getTenNongsan());
                Delete(arrNongsan.get(position).getId());
            }
        });
    }

//    //
//    private void showDialog(final int id, String tenns){
//        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
//        //final AlertDialog show = dialog.show();
//        dialog.setMessage("Bạn có chắn chắn xóa " + tenns + " không?");
//        dialog.setPositiveButton("C", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Delete(id);
//            }
//        });
//        dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                //show.dismiss();
//            }
//        });
//        dialog.show();
//    }

    public void alertMessage(final int id) {
        DialogInterface.OnClickListener dialogClickListener = new  DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE: // Yes button clicked
                        Delete(id);
                        // set your own logic for removal item from listview
                        break;

                    case DialogInterface.BUTTON_NEGATIVE: // No button clicked // do nothing
                        //Delete(which);
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    //
    public void Delete(int id){
        NongDanService nongDanService = ApiClient.getClient().create(NongDanService.class);
        Call<POST> call = nongDanService.deletens(id);
        call.enqueue(new Callback<POST>() {
            @Override
            public void onResponse(Call<POST> call, Response<POST> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Xóa thất bại, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<POST> call, Throwable t) {
                Toast.makeText(context, "Lỗi kết nối, vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrNongsan.size();
    }

    public class TinDaDangViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView TenNongSan, EndDate, SDT, DiaChi;
        ImageView ImgEdit;
        ItemLongClickListener itemLongClickListener;

        public TinDaDangViewHolder(View itemView) {
            super(itemView);
            TenNongSan = itemView.findViewById(R.id.tenns_tindadang_tv);
            EndDate = itemView.findViewById(R.id.date_tindadang_tv);
            SDT = itemView.findViewById(R.id.sdt_tindadang_tv);
            ImgEdit = itemView.findViewById(R.id.edit_tindadang_img);
            DiaChi = itemView.findViewById(R.id.diachi_tindadang_tv);
            //
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            this.itemLongClickListener.onItemLongClick(view,getLayoutPosition());
            return false;
        }

        //
        public void setItemLongClickListener(ItemLongClickListener ic)
        {
            this.itemLongClickListener=ic;
        }
    }

    //
    public interface ItemLongClickListener {
        void onItemLongClick(View v,int pos);
    }
}
