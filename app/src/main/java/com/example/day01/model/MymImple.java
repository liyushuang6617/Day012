package com.example.day01.model;

import com.example.day01.api.MyService;
import com.example.day01.bean.CollcetBean;
import com.example.day01.callback.CallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MymImple implements Mym {
    @Override
    public void getData(final CallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyService.collceturl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Observable<CollcetBean> collcet = retrofit.create(MyService.class).collcet();
        collcet.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CollcetBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CollcetBean collcetBean) {
                        if (collcetBean != null) {
                            callBack.onSuccess(collcetBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
