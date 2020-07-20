package com.skyhosting.softxpertandroidtask.Model.Api;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;

public class ApiHelper {

    private final ApiService apiService;

    public ApiHelper(@NotNull ApiService apiService) {
        this.apiService = apiService;
    }

    @NotNull
    public final Single<Object> getCarsData() {
        Single carsData = this.apiService.getCarsData();
        return carsData;
    }

}
