package com.example.gouwuche2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gouwuche2.R;
import com.example.gouwuche2.bean.ArtBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvAdapter extends RecyclerView.Adapter<RlvAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ArtBean.DataBean> list;
    private ArrayList<Boolean> booleans;

    public RlvAdapter(Context context, ArrayList<ArtBean.DataBean> list) {
        this.context = context;
        this.list = list;

    }

    public void setList(ArrayList<ArtBean.DataBean> list) {
        this.list = list;
        booleans = new ArrayList<>();
        for (int j = 0; j < list.size(); j++) {
            booleans.add(false);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        viewHolder.cb.setChecked(booleans.get(i));
        viewHolder.tv1.setText(list.get(i).getFood_str());
        viewHolder.tv2.setText("价格:" + list.get(i).getCollect_num() + "元");
        Glide.with(context).load(list.get(i).getPic()).into(viewHolder.iv);

        viewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (a != null) {
                        a.isChecked(i, list.get(i));
                    }
                } else {
                    if (a != null) {
                        a.isNoChecked(i, list.get(i));
                    }
                }
            }
        });
        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(booleans.get(i)){
                    booleans.set(i,false);
                    viewHolder.cb.setChecked(false);
                }else{
                    booleans.set(i,true);
                    viewHolder.cb.setChecked(true);
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cb)
        CheckBox cb;
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv1)
        TextView tv1;
        @BindView(R.id.tv2)
        TextView tv2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private A a;

    public void setA(A a) {
        this.a = a;
    }

    public interface A {
        void isChecked(int pos, ArtBean.DataBean bean);

        void isNoChecked(int pos, ArtBean.DataBean bean);
    }
}
