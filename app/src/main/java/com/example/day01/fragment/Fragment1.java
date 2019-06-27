package com.example.day01.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.day01.R;
import com.example.day01.adapter.RlvF1Adapter;
import com.example.day01.api.MyService;
import com.example.day01.bean.ArtBean;
import com.example.day01.bean.ListBean;
import com.example.day01.utils.DbUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment implements RlvF1Adapter.A {


    private View view;
    private RecyclerView mRe;
    private SmartRefreshLayout mSmart;
    private RlvF1Adapter adapter;
    private ArrayList<ArtBean.DataBean.DatasBean> artlist;

    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_fragment1, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyService.arturl)
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
                        artlist.addAll(artBean.getData().getDatas());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initView(View inflate) {
        mRe = (RecyclerView) inflate.findViewById(R.id.re);
        mSmart = (SmartRefreshLayout) inflate.findViewById(R.id.smart);

        mRe.setLayoutManager(new LinearLayoutManager(getActivity()));

        artlist = new ArrayList<>();
        adapter = new RlvF1Adapter(getActivity(), artlist);
        mRe.setAdapter(adapter);

        adapter.setA(this);
        registerForContextMenu(mRe);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(1, 1, 1, "删除");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                artlist.remove(artlist.get(pos1));
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private int pos1;

    @Override
    public void onClick(int pos, ArtBean.DataBean.DatasBean bean) {
        this.pos1 = pos;
        ListBean listBean = new ListBean();
        listBean.setId(null);
        listBean.setTitle(bean.getTitle());
        listBean.setChapterName(bean.getChapterName());
        listBean.setEnvelopePic(bean.getEnvelopePic());
        DbUtils.getDbUtils().delete(listBean);

        Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
    }
}
