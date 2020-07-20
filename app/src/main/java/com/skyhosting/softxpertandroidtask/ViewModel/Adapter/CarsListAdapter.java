package com.skyhosting.softxpertandroidtask.ViewModel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.skyhosting.softxpertandroidtask.Model.Car;
import com.skyhosting.softxpertandroidtask.R;
import com.skyhosting.softxpertandroidtask.View.Listeners.OnLoadMoreListener;

import java.util.List;

public class CarsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = CarsListAdapter.class.getSimpleName();

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private Context context;

    private List<Car> cars;

    // before loading more.
    private int visibleThreshold = 10;
    private int scrollOutItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isScroll;

    public CarsListAdapter(Context context, List<Car> cars, RecyclerView recyclerView) {
        this.context = context;
        this.cars = cars;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();


            recyclerView
                    .addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView,
                                               int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
//                            int currentItems = linearLayoutManager.getChildCount();
                            totalItemCount = linearLayoutManager.getItemCount();
                            scrollOutItem = linearLayoutManager
                                    .findLastVisibleItemPosition();


//                            if (dy > 0) {



                                if (!loading&& (totalItemCount)<=(scrollOutItem+visibleThreshold))
                                {
                                    // End has been reached
                                    // Do something
                                    if (onLoadMoreListener != null) {
                                        onLoadMoreListener.onLoadMore();
                                    }
                                    loading = true;
                                }

                            }
//                        }

                        @Override
                        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                            super.onScrollStateChanged(recyclerView, newState);

                        }
                    });
        }

    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    public void appendNewPageCars(List<Car> cars) {
        cars.addAll(cars);
    }

    public void addLoadingItem(Car car){
        cars.add(car);
    }

    @Override
    public int getItemViewType(int position) {
        return cars.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        if (viewType == VIEW_TYPE_ITEM) {
            View view = inflater.inflate(R.layout.car_list_item, parent, false);
            return new CarsViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof CarsViewHolder) {
            Car car = cars.get(position);

            configureCarViews(car, (CarsViewHolder) holder);
        } else if (holder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) holder, position);
        }


    }

    public class CarsViewHolder extends RecyclerView.ViewHolder {
        public ImageView mCarImage;

        public TextView mCarBrand, mCarConstYear, mCarIsUsed;

        public CarsViewHolder(@NonNull View itemView) {
            super(itemView);

            initCarViews(itemView);
        }


        private void initCarViews(View itemView) {
            mCarImage = (ImageView) itemView.findViewById(R.id.car_image);

            mCarBrand = (TextView) itemView.findViewById(R.id.car_brand);
            mCarConstYear = (TextView) itemView.findViewById(R.id.const_year);
            mCarIsUsed = (TextView) itemView.findViewById(R.id.is_used);

        }

    }

    private void configureCarViews(Car car, CarsViewHolder holder) {
        Glide.with(context)
                .load(car.getImageUrl())
                .placeholder(R.drawable.car)
                .into(holder.mCarImage);

        holder.mCarBrand.setText(car.getBrand());

//        String constYearFormatted = formatConstructionYearDate(car.getConstructionYear());

        holder.mCarConstYear.setText(car.getConstructionYear());

//        Log.d(TAG, "constYearFormatted: " + constYearFormatted);


        if (car.getIsUsed()) {
            holder.mCarIsUsed.setText("Used");
            holder.mCarIsUsed.setTextColor(context.getResources().getColor(android.R.color.darker_gray));
        } else {
            holder.mCarIsUsed.setText("New");
            holder.mCarIsUsed.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }

    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }

//    private String formatConstructionYearDate(String constYear) {
//        String[] separated = constYear.split(".");
//        String month = separated[0]; // this will contain "Month"
//        String year = separated[1]; // this will contain "Year"
//
//        return month + "/" + year;
//    }

}
