package com.skyhosting.softxpertandroidtask.Model.Api;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;

public interface ApiService {

    @NotNull
    Single getCarsData();

}
