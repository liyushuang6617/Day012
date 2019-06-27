package com.example.day01.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.day01.R;
import com.example.day01.adapter.RlvF4Adapter;
import com.example.day01.bean.ListBean;
import com.example.day01.utils.DbUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment4 extends Fragment implements RlvF4Adapter.A {


    private View view;
    private RecyclerView mRe;
    private ArrayList<ListBean> list;
    ;
    private RlvF4Adapter adapter;

    public Fragment4() {
        // Required empty public constructor
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            initData();
        } else {
            list.clear();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_fragment4, container, false);
        initView(inflate);
//        initData();
        return inflate;
    }

    private void initView(View inflate) {
        mRe = (RecyclerView) inflate.findViewById(R.id.re);
        list = new ArrayList<>();
        adapter = new RlvF4Adapter(getActivity(), list);
        mRe.setAdapter(adapter);

        mRe.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setA(this);
    }


    private void initData() {
        List<ListBean> query = DbUtils.getDbUtils().query();
        list.addAll(query);
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(int pos, ListBean bean) {
        DbUtils.getDbUtils().delete(bean);
        list.remove(pos);
        adapter.notifyDataSetChanged();
    }
}
