package com.example.day01.callback;

import com.example.day01.bean.CollcetBean;

public interface CallBack {

    void onSuccess(CollcetBean collcetBean);

    void onFail(String msg);
}
