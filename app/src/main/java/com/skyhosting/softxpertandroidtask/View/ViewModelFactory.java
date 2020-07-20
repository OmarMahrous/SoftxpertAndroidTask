package com.skyhosting.softxpertandroidtask.View;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.skyhosting.softxpertandroidtask.Model.Api.ApiHelper;
import com.skyhosting.softxpertandroidtask.Model.Repository.CarsRepository;
import com.skyhosting.softxpertandroidtask.ViewModel.CarsListViewModel;

import org.jetbrains.annotations.NotNull;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final ApiHelper apiHelper;


    @Override
    public ViewModel create(@NotNull Class modelClass) {
//        Intrinsics.checkParameterIsNotNull(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(CarsListViewModel.class)) {
            return (ViewModel) (new CarsListViewModel(new CarsRepository(this.apiHelper)));
        }  else {
            try {
                throw (Throwable) (new IllegalArgumentException("Unknown class name"));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }


    public ViewModelFactory(@NotNull ApiHelper apiHelper) {
//        Intrinsics.checkParameterIsNotNull(apiHelper, "apiHelper");
//        super();
        this.apiHelper = apiHelper;

    }}
