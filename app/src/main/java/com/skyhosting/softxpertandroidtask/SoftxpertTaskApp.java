package com.skyhosting.softxpertandroidtask;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class SoftxpertTaskApp extends Application implements LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onMoveToForeground() {
        // app moved to foreground
//        Toast.makeText(appContext, "App moved to foreground", Toast.LENGTH_SHORT).show();


    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onMoveToBackground() {
        // app moved to background
//        Toast.makeText(appContext, "App moved to background", Toast.LENGTH_SHORT).show();


    }

}
