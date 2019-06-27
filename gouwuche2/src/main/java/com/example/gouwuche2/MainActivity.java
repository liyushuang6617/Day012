package com.example.gouwuche2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.gouwuche2.adapter.RlvAdapter;
import com.example.gouwuche2.api.MyService;
import com.example.gouwuche2.bean.ArtBean;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


//李玉爽  1811A
public class MainActivity extends AppCompatActivity implements RlvAdapter.A {

    private TextView mTv;
    private RecyclerView mRe;
    private ArrayList<ArtBean.DataBean> list;
    private RlvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Observable<ArtBean> art = retrofit.create(MyService.class).art();
        art.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArtBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArtBean artBean) {
                        if (artBean != null) {
                            list.addAll(artBean.getData());
                            adapter.setList(list);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("MainActivity", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
        mRe = (RecyclerView) findViewById(R.id.re);

        list = new ArrayList<>();
        adapter = new RlvAdapter(this, list);
        mRe.setAdapter(adapter);
        mRe.setLayoutManager(new LinearLayoutManager(this));

        adapter.setA(this);
    }

    private double a;

    @Override
    public void isChecked(int pos, ArtBean.DataBean bean) {
        String collect_num = bean.getCollect_num();
        double v = Double.parseDouble(collect_num);
        a += v;
        mTv.setText("合计:" + a + "元");
    }

    @Override
    public void isNoChecked(int pos, ArtBean.DataBean bean) {
        String collect_num = bean.getCollect_num();
        double v = Double.parseDouble(collect_num);
        a -= v;
        mTv.setText("合计:" + a + "元");
    }
}
