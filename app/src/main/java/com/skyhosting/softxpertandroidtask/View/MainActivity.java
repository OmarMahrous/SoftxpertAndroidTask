package com.skyhosting.softxpertandroidtask.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.skyhosting.softxpertandroidtask.R;
import com.skyhosting.softxpertandroidtask.Utils.NetworkStateChangeReceiver;

public class MainActivity extends AppCompatActivity implements NetworkStateChangeReceiver.ConnectivityReceiverListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }
}