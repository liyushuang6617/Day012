package com.example.day01.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day01.Main5Activity;
import com.example.day01.bean.CollcetBean;

import java.util.ArrayList;

public class RlvF2Adapter extends RecyclerView.Adapter<RlvF2Adapter.ViewHolder> {
    private Context context;
    private ArrayList<CollcetBean.ResultsBean> list;

    public RlvF2Adapter(Context context, ArrayList<CollcetBean.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, android.R.layout.activity_list_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tv.setText(list.get(i).getWho());
        Glide.with(context).load(list.get(i).getUrl()).into(viewHolder.iv);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a != null) {
                    a.onClick(i, list.get(i));
                }
            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context, Main5Activity.class);
                context.startActivity(intent);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(android.R.id.icon);
            tv = itemView.findViewById(android.R.id.text1);
        }
    }
    private A a;

    public void setA(A a) {
        this.a = a;
    }

    public interface A{
        void onClick(int pos,CollcetBean.ResultsBean bean);
    }
}
