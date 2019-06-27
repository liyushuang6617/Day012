package com.example.day01.view;

import com.example.day01.bean.CollcetBean;

public interface MyView {

    void onSuccess(CollcetBean collcetBean);

    void onFail(String msg);
}
