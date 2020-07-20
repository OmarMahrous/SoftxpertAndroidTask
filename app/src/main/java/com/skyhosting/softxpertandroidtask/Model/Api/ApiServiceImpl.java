package com.skyhosting.softxpertandroidtask.Model.Api;

import android.app.Activity;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.skyhosting.softxpertandroidtask.Model.CarsResponse;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.OkHttpClient;

public class ApiServiceImpl implements ApiService {


    private final String BASE_URL = "http://demo1286023.mockable.io/api/v1/cars?page={page}";

    private int page;


    public ApiServiceImpl(int page) {
        this.page = page;
    }


    public ApiServiceImpl() {
    }


    @NotNull
    @Override
    public Single getCarsData() {
        return Rx2AndroidNetworking.get(BASE_URL)
                .setOkHttpClient(okHttpClient())
                .addPathParameter("page", String.valueOf(page))
                .build()

                .getObjectSingle(CarsResponse.class)
//                .getObjectListSingle(DepartmentsResponse.class)
//                .subscribeOn(Schedulers.io()) // do the network call on another thread
//                .observeOn(AndroidSchedulers.mainThread()) // return the result in mainThread
                ;
    }

    private OkHttpClient okHttpClient() {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        return okHttpClient;
    }


}
