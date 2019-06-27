package com.example.day01.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day01.R;
import com.example.day01.bean.ListBean;

import java.util.ArrayList;

public class RlvF4Adapter extends RecyclerView.Adapter<RlvF4Adapter.ViewHolder> {
    private Context context;
    private ArrayList<ListBean> list;

    public RlvF4Adapter(Context context, ArrayList<ListBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(ArrayList<ListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_f1_art, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tv1.setText(list.get(i).getTitle());
        viewHolder.tv2.setText(list.get(i).getChapterName());
        Glide.with(context).load(list.get(i).getEnvelopePic()).into(viewHolder.iv);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a!=null){
                    a.onClick(i,list.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv1;
        TextView tv2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
        }
    }
    private A a;

    public void setA(A a) {
        this.a = a;
    }

    public interface A{
        void onClick(int pos,ListBean bean);
    }
}
