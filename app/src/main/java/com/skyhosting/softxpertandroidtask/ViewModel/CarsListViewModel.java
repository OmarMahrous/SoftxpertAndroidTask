package com.skyhosting.softxpertandroidtask.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skyhosting.softxpertandroidtask.Model.CarsResponse;
import com.skyhosting.softxpertandroidtask.Model.Repository.CarsRepository;
import com.skyhosting.softxpertandroidtask.Utils.Resource;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CarsListViewModel extends ViewModel {

    private final MutableLiveData<Resource> carsData = new MutableLiveData<Resource>();

    private final CompositeDisposable compositeDisposable;
    private final CarsRepository carsRepository;

    private void fetchCarsData() {
        carsData.postValue(Resource.loading((Object) null));
        this.compositeDisposable.add(this.carsRepository.getCarsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Consumer<Object>) (new Consumer<Object>() {
                    public final void accept(Object userList) {
                        String successMessage = ((CarsResponse) userList).getMessage();


                        carsData.postValue(Resource.success(userList, "Success"));
                    }
                }), (Consumer<Throwable>) (new Consumer<Throwable>() {
                    // $FF: synthetic method
                    // $FF: bridge method
//            public void accept(Throwable var1) {
//                this.accept(var1);
//            }

                    public final void accept(Throwable throwable) {


                        if (throwable instanceof TimeoutException)
                            carsData.postValue(Resource.error("Connection Timeout !", (Object) null));
                        else if (throwable instanceof IOException)
                            carsData.postValue(Resource.error("Timeout !", (Object) null));
                        else if (throwable instanceof SocketTimeoutException)
                            carsData.postValue(Resource.error("Connection Timeout !", (Object) null));
                        else
                            carsData.postValue(Resource.error("Page not found !", (Object) null));
                    }
                })));
    }


    protected void onCleared() {
        super.onCleared();
        this.compositeDisposable.dispose();
    }

    @NotNull
    public final LiveData<Resource> getCarsData() {
        return (LiveData<Resource>) carsData;
    }


    public CarsListViewModel(@NotNull CarsRepository carsRepository) {
//        Intrinsics.checkParameterIsNotNull(loginRepository, "departmentRepository");
//        super();

        this.carsRepository = carsRepository;
        this.compositeDisposable = new CompositeDisposable();

        this.fetchCarsData();


    }
}