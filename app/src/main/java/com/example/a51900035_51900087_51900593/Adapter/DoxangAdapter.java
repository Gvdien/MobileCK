package com.example.a51900035_51900087_51900593.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a51900035_51900087_51900593.Activity.ViewLichsuDoxangActivity;
import com.example.a51900035_51900087_51900593.Model.Doxang;
import com.example.a51900035_51900087_51900593.R;

import java.util.List;

public class DoxangAdapter extends RecyclerView.Adapter<DoxangAdapter.ViewHolder>{
    private List<Doxang> lstDoxang;
    private Context mContext;
    public DoxangAdapter(Context context, List<Doxang> lstDoxang) {
        this.mContext = context;
        this.lstDoxang = lstDoxang;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,
                parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Doxang ls = lstDoxang.get(position);
        if(ls==null)
            return;
        holder.img.setImageResource(ls.getPic());
        holder.tvNoithuchien.setText(ls.getNoithuchien().toUpperCase());
        holder.tvChiphi.setText(ls.getChiphi());
        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDetail(ls);
            }
        });
    }
    private void onClickDetail(Doxang ls) {
        Intent i = new Intent(mContext, ViewLichsuDoxangActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_doxang", ls);
        i.putExtras(bundle);
        mContext.startActivity(i);
    }
    @Override
    public int getItemCount() {
        if(lstDoxang !=null)
            return lstDoxang.size();
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView layout_item;
        ImageView img;
        TextView tvNoithuchien, tvChiphi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvNoithuchien =  itemView.findViewById(R.id.tvNoithuchien);
            tvChiphi = itemView.findViewById(R.id.tvChiphi);
            layout_item = itemView.findViewById(R.id.layout_item);
        }
    }
    public void release(){
        mContext=null;
    }
}
