package com.skyhosting.softxpertandroidtask.Model.Repository;

import com.skyhosting.softxpertandroidtask.Model.Api.ApiHelper;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;

public class CarsRepository {

    private final ApiHelper apiHelper;

    @NotNull
    public final Single<Object> getCarsData() {
        return this.apiHelper.getCarsData();
    }






    public CarsRepository(@NotNull ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

}
