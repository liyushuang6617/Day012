package com.example.gouwuche1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gouwuche1.adapter.RlvMyAdapter;
import com.example.gouwuche1.bean.ArtBean;
import com.example.gouwuche1.model.MyMImple;
import com.example.gouwuche1.presenter.MyP;
import com.example.gouwuche1.presenter.MyPImple;
import com.example.gouwuche1.view.MyV;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyV, RlvMyAdapter.A {

    private TextView mTv;
    private RecyclerView mRe;
    private ArrayList<ArtBean.DataBean> list;
    private RlvMyAdapter adapter;
    private ArrayList<Boolean> booleans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
        mRe = (RecyclerView) findViewById(R.id.re);

        list = new ArrayList<>();
        booleans = new ArrayList<>();
        adapter = new RlvMyAdapter(this, list,booleans);
        mRe.setAdapter(adapter);
        mRe.setLayoutManager(new LinearLayoutManager(this));

        adapter.setA(this);
    }

    private void initData() {
        MyP myP = new MyPImple(new MyMImple(), this);
        myP.getData();
    }

    @Override
    public void onSuccess(ArtBean artBean) {
        list.addAll(artBean.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String msg) {

    }

    private double a;

    @Override
    public void onClick(int pos, ArtBean.DataBean bean) {
//        Toast.makeText(this, bean.getCollect_num(), Toast.LENGTH_SHORT).show();
        String collect_num = bean.getCollect_num();
        double v = Double.parseDouble(collect_num);
        a += v;
        mTv.setText("合计:" + a + "元");
    }

    @Override
    public void onNoChecked(int pos, ArtBean.DataBean bean) {
        String collect_num = bean.getCollect_num();
        double v = Double.parseDouble(collect_num);
        a -= v;
        mTv.setText("合计:" + a + "元");
    }
}
