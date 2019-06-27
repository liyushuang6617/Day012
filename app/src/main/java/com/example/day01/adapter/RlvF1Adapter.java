package com.example.day01.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.day01.R;
import com.example.day01.bean.ArtBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class RlvF1Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<ArtBean.DataBean.DatasBean> artlist;

    public RlvF1Adapter(Context context, ArrayList<ArtBean.DataBean.DatasBean> artlist) {
        this.context = context;
        this.artlist = artlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0) {
            View view = View.inflate(context, R.layout.item_f1_ban, null);
            return new ViewHolder1(view);
        } else if (i == 1) {
            View view = View.inflate(context, R.layout.item_f1_art, null);
            return new ViewHolder2(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof ViewHolder1) {
            ViewHolder1 viewHolder1 = (ViewHolder1) viewHolder;
            viewHolder1.ban.setImages(artlist).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    ArtBean.DataBean.DatasBean p = (ArtBean.DataBean.DatasBean) path;
                    Glide.with(context).load(p.getEnvelopePic()).into(imageView);
                }
            }).start();
        } else if (viewHolder instanceof ViewHolder2) {
            ViewHolder2 viewHolder2 = (ViewHolder2) viewHolder;
            int pos = 0;
            if (artlist.size() > 0) {
                pos = i - 1;
            } else {
                pos = i;
            }
            viewHolder2.tv1.setText(artlist.get(pos).getTitle());
            viewHolder2.tv2.setText(artlist.get(pos).getChapterName());
            if (pos % 2 == 0) {
                RequestOptions requestOptions = RequestOptions.circleCropTransform();
                Glide.with(context).load(artlist.get(pos).getEnvelopePic()).apply(requestOptions).into(viewHolder2.iv);
            } else {
                RequestOptions requestOptions = RequestOptions.bitmapTransform(new RoundedCorners(10));
                Glide.with(context).load(artlist.get(pos).getEnvelopePic()).apply(requestOptions).into(viewHolder2.iv);
            }
            viewHolder2.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (a != null) {
                        a.onClick(i, artlist.get(i));
                    }
                }
            });

            viewHolder2.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return artlist.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        Banner ban;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            ban = itemView.findViewById(R.id.ban);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv1;
        TextView tv2;

        public ViewHolder2(@NonNull View itemView) {
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

    public interface A {
        void onClick(int pos, ArtBean.DataBean.DatasBean bean);
    }
}
