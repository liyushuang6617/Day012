package com.example.gouwuche1.callback;

import com.example.gouwuche1.bean.ArtBean;

public interface CallBack {

    void onSuccess(ArtBean artBean);

    void onFail(String msg);
}
