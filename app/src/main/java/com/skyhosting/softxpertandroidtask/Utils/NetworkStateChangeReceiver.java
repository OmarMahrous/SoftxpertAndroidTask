package com.skyhosting.softxpertandroidtask.Utils;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.skyhosting.softxpertandroidtask.R;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class NetworkStateChangeReceiver extends BroadcastReceiver {

    private ConnectivityReceiverListener connectivityReceiverListener;

    public NetworkStateChangeReceiver(ConnectivityReceiverListener connectivityReceiverListener) {
        this.connectivityReceiverListener = connectivityReceiverListener;
    }

    public NetworkStateChangeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        Intent networkStateIntent = new Intent(NETWORK_AVAILABLE_ACTION);
//        networkStateIntent.putExtra(IS_NETWORK_AVAILABLE, isConnectedToInternet(context));

//        LocalBroadcastManager.getInstance(context).sendBroadcast(networkStateIntent);

        boolean isConnected = isConnectedToInternet(context);

        String networkStatus = isConnected ? "Connected !" : "Disconnected !";

        Helper.toast(context, networkStatus);


        if (connectivityReceiverListener != null) {
            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
        }


    }

    private boolean isConnectedToInternet(Context context) {
        try {
            if (context != null) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
            return false;
        } catch (Exception e) {
            Log.e(NetworkStateChangeReceiver.class.getName(), e.getMessage());
            return false;
        }
    }

    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }

    public void showNoInternetPopup(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
//                .setIcon(R.drawable.ic_action_warning)
                .setCancelable(true)
                .setMessage("There is no Internet Connection")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
