package com.skyhosting.softxpertandroidtask.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.skyhosting.softxpertandroidtask.R;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        progressDialog = new ProgressDialog(this);


    }

    public void showLoading(String loadingTitle) {
        progressDialog.setTitle(loadingTitle);
        progressDialog.setMessage("Loading, Please Wait...");
        progressDialog.show();

//        mHandler.sendMessageDelayed(new Message(), 5000);
    }

    public boolean isLoading() {
        return progressDialog.isShowing();
    }

    public void hideLoading() {
        progressDialog.cancel();
        progressDialog.dismiss();
        progressDialog.hide();
    }
}