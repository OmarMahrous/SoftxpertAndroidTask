package com.skyhosting.softxpertandroidtask.ViewModel.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skyhosting.softxpertandroidtask.Model.Car;

import java.util.List;

public class CarsListAdapter extends RecyclerView.Adapter<CarsListAdapter.CarsViewHolder>{


    @NonNull
    @Override
    public CarsListAdapter.CarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull CarsListAdapter.CarsViewHolder holder, int position) {

    }

    public class CarsViewHolder extends RecyclerView.ViewHolder {
        public CarsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
