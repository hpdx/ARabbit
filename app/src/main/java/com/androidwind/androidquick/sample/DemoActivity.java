package com.androidwind.androidquick.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.androidwind.androidquick.module.glide.GlideManager;
import com.androidwind.androidquick.module.retrofit.RetrofitManager;
import com.androidwind.androidquick.module.retrofit.exeception.ApiException;
import com.androidwind.androidquick.module.rxjava.BaseObserver;
import com.androidwind.androidquick.util.LogUtil;
import com.androidwind.androidquick.util.RxUtil;
import com.androidwind.androidquick.util.ToastUtil;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class DemoActivity extends BaseActivity {

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    public void OpenNetwork(View v) {
        //使用sdk自带的RetrofitManager, 返回Json字符串格式
        RetrofitManager.getInstance().createApi(GankApis.class)
                .getHistoryDate()
                .compose(RxUtil.<GankRes<List<String>>>applySchedulers())
                .compose(this.<GankRes<List<String>>>bindToLifecycle())
                .subscribe(new BaseObserver<GankRes<List<String>>>() {

                    @Override
                    public void onError(ApiException exception) {
                        LogUtil.e(TAG, "error:" + exception.getMessage());
                        ToastUtil.showToast("Fail!");
                    }

                    @Override
                    public void onSuccess(GankRes<List<String>> listGankRes) {
                        LogUtil.i(TAG, listGankRes.getResults().toString());
                        ToastUtil.showToast("Success!");
                    }
                });

        //新建一个Retrofit, 返回普通string字符串格式
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .client(RetrofitManager.getInstance().getOkHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())//添加 string 转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加 RxJava 适配器
                .build();
        retrofit.create(GankApis.class)
                .getSdkVersion()
                .compose(RxUtil.<String>applySchedulers())
                .compose(this.<String>bindToLifecycle())
                .subscribe(new BaseObserver<String>() {

                               @Override
                               public void onError(ApiException exception) {

                               }

                               @Override
                               public void onSuccess(String html) {

                               }
                           }
                );
    }

    public void OpenImage(View v) {
        GlideManager
                .loadNet("https://hbimg.huabanimg.com/ca0077ed805df7a1566d68a74cae73c59994929b73f68-nkFwhw_fw658", (ImageView) findViewById(R.id.imageView));
    }
}