package com.example.gouwuche1.presenter;

import com.example.gouwuche1.bean.ArtBean;
import com.example.gouwuche1.callback.CallBack;
import com.example.gouwuche1.model.MyM;
import com.example.gouwuche1.view.MyV;

public class MyPImple implements MyP, CallBack {

    private MyM myM;
    private MyV myV;

    public MyPImple(MyM myM, MyV myV) {
        this.myM = myM;
        this.myV = myV;
    }

    @Override
    public void getData() {
        if (myM != null) {
            myM.getData(this);
        }
    }

    @Override
    public void onSuccess(ArtBean artBean) {
        myV.onSuccess(artBean);
    }

    @Override
    public void onFail(String msg) {
        myV.onFail(msg);
    }
}
