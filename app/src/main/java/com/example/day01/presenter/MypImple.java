package com.example.day01.presenter;

import com.example.day01.bean.CollcetBean;
import com.example.day01.callback.CallBack;
import com.example.day01.model.Mym;
import com.example.day01.view.MyView;

public class MypImple implements Myp, CallBack {

    private Mym mym;
    private MyView myView;

    public MypImple(Mym mym, MyView myView) {
        this.mym = mym;
        this.myView = myView;
    }

    @Override
    public void getData() {
        if (mym != null) {
            mym.getData(this);
        }
    }

    @Override
    public void onSuccess(CollcetBean collcetBean) {
        myView.onSuccess(collcetBean);
    }

    @Override
    public void onFail(String msg) {
        myView.onFail(msg);
    }
}
