package com.skyhosting.softxpertandroidtask.Utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.StringRes;

import com.google.android.material.snackbar.Snackbar;

public class Helper {

    public static void snackbar(Activity activity, @StringRes String id) {
        Snackbar.make(activity.findViewById(android.R.id.content), id, Snackbar.LENGTH_SHORT).show();
    }

    public static void toast(Context context, String id) {
        Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
    }

}
