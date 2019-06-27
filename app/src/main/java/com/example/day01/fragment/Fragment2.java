package com.example.day01.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.day01.R;
import com.example.day01.adapter.RlvF2Adapter;
import com.example.day01.bean.CollcetBean;
import com.example.day01.model.MymImple;
import com.example.day01.presenter.Myp;
import com.example.day01.presenter.MypImple;
import com.example.day01.view.MyView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment implements MyView, RlvF2Adapter.A {


    private View view;
    private RecyclerView mRe;
    private ArrayList<CollcetBean.ResultsBean> list;
    private RlvF2Adapter adapter;
    private PopupWindow pw;

    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_fragment2, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        Myp myp = new MypImple(new MymImple(), this);
        myp.getData();
    }

    private void initView(View inflate) {
        mRe = (RecyclerView) inflate.findViewById(R.id.re);

        list = new ArrayList<>();
        adapter = new RlvF2Adapter(getActivity(), list);
        mRe.setAdapter(adapter);
        mRe.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setA(this);

        View view1 = View.inflate(getContext(), R.layout.pop_item, null);
        pw = new PopupWindow(view1, 200, 200);
        pw.setAnimationStyle(R.style.pop);

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
            }
        });

        Button bt = view1.findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(pos1);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onSuccess(CollcetBean collcetBean) {
        list.addAll(collcetBean.getResults());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String msg) {

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    private int pos1;

    @Override
    public void onClick(int pos, CollcetBean.ResultsBean bean) {
        this.pos1 = pos;
        pw.showAsDropDown(mRe);
    }
}
