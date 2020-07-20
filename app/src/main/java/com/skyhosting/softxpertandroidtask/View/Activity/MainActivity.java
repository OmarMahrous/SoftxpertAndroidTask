package com.skyhosting.softxpertandroidtask.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.skyhosting.softxpertandroidtask.Model.Api.ApiHelper;
import com.skyhosting.softxpertandroidtask.Model.Api.ApiServiceImpl;
import com.skyhosting.softxpertandroidtask.Model.Car;
import com.skyhosting.softxpertandroidtask.Model.CarsResponse;
import com.skyhosting.softxpertandroidtask.R;
import com.skyhosting.softxpertandroidtask.Utils.Helper;
import com.skyhosting.softxpertandroidtask.Utils.NetworkStateChangeReceiver;
import com.skyhosting.softxpertandroidtask.Utils.Resource;
import com.skyhosting.softxpertandroidtask.View.Listeners.EndlessScrollListener;
import com.skyhosting.softxpertandroidtask.View.Listeners.OnLoadMoreListener;
import com.skyhosting.softxpertandroidtask.View.MySwipeToRefresh;
import com.skyhosting.softxpertandroidtask.View.ViewModelFactory;
import com.skyhosting.softxpertandroidtask.ViewModel.Adapter.CarsListAdapter;
import com.skyhosting.softxpertandroidtask.ViewModel.CarsListViewModel;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;
import static com.skyhosting.softxpertandroidtask.Utils.Status.LOADING;

public class MainActivity extends BaseActivity implements NetworkStateChangeReceiver.ConnectivityReceiverListener
        , SwipeRefreshLayout.OnRefreshListener {

    private final String TAG = MainActivity.class.getSimpleName();

    private CarsListAdapter carsAdapter;

    private List<Car> mCarsList = new ArrayList<>();
    private RecyclerView mCarsRecView;

    int carPage = 1;
    private CarsListViewModel carsListViewModel;
    private MySwipeToRefresh mSwipeRLayout;
    private boolean isLoading = false;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver(new NetworkStateChangeReceiver(this),
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        initViews();

        carsAdapter = new CarsListAdapter(this, mCarsList);

        populateCarsData(mCarsList);

        getCarsData(carPage);


    }


    private void initViews() {
        mSwipeRLayout = (MySwipeToRefresh) findViewById(R.id.swipe_refresh_layout);
        mSwipeRLayout.setColorSchemeResources(R.color.colorPrimary
                , R.color.colorPrimaryDark
                , R.color.colorAccent);
        mSwipeRLayout.setRefreshing(true);
        mSwipeRLayout.setOnRefreshListener(this);

        progressBar = findViewById(R.id.progressBar);

        mCarsRecView = (RecyclerView) findViewById(R.id.cars_rec_view);
        linearLayoutManager = new LinearLayoutManager(this);
        mCarsRecView.setLayoutManager(linearLayoutManager);
        ViewCompat.setNestedScrollingEnabled(mCarsRecView, false);
        EndlessScrollListener endlessScrollListener = new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                carPage++;
                if (carPage < 5) {
                    progressBar.setVisibility(View.VISIBLE);
                    getCarsData(carPage);
                } else {
                    setupTopSnackbar(Color.RED, "No more data found !");
                    progressBar.setVisibility(View.GONE);
                }

                Log.d(TAG, "carPage: " + carPage);
            }
        };

        mCarsRecView.addOnScrollListener(endlessScrollListener);
    }

    private void setupTopSnackbar(int backgroundColor, String message) {
        TSnackbar snackbar = TSnackbar.make(findViewById(android.R.id.content), message, TSnackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.WHITE);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(backgroundColor);
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }


    private void populateCarsData(List<Car> cars) {
        mCarsList.addAll(cars);
        mCarsRecView.setAdapter(carsAdapter);
        carsAdapter.refresh();
        carsAdapter.notifyItemRangeInserted(carsAdapter.getItemCount(), mCarsList.size() - 1);

    }

    private void getCarsData(int page) {
        setupCarsViewModel(page);
        setupCarsObserver();
    }

    private void setupCarsViewModel(int page) {
        showLoading("Loading Cars data !");


        carsListViewModel = ViewModelProviders.of(
                this,
                new ViewModelFactory(new ApiHelper(new ApiServiceImpl(page)))
        ).get(CarsListViewModel.class);
    }

    private void setupCarsObserver() {
        carsListViewModel.getCarsData().observe(this, new Observer<Resource>() {
            @Override
            public void onChanged(Resource resource) {

                switch (resource.getStatus()) {
                    case SUCCESS:
                        progressBar.setVisibility(View.GONE);
                        mSwipeRLayout.setRefreshing(false);

                        hideLoading();

                        Log.d(TAG, "SUCCESS");


                        if (resource.getData() != null) {
//                            bookingRequests.clear();

                            CarsResponse carsResponse = (CarsResponse) resource.getData();


                            populateCarsData(carsResponse.getData());


                        }


                        break;
                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        mSwipeRLayout.setRefreshing(false);

                        hideLoading();

                        Log.d(TAG, "ERROR");

                        Helper.toast(MainActivity.this, resource.getMessage());
                        break;
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);

                        break;
                }
            }
        });
    }

    private void prepareEndlessScroll() {


    }


    @Override
    public void onRefresh() {
        // Your code here
        getCarsData(1);

        // To keep animation for 5 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Stop animation (This will be after 5 seconds)
//                mSwipeRLayout.setRefreshing(false);
            }
        }, 5000); // Delay in millis
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected)
            setupTopSnackbar(Color.GREEN, "Connected !");
        else
            setupTopSnackbar(Color.RED, "No Internet Connection !");
    }
}