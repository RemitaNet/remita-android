package com.remita.paymentsdk.core;

import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

public class BaseActivity extends AppCompatActivity {

    protected  void setWidthSize(double widthSize, double heightSize) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * widthSize);
        int screenHeight = (int) (metrics.heightPixels * heightSize);
        getWindow().setLayout(screenWidth, screenHeight);
    }

    @Override
    public void onBackPressed() {

    }
}
