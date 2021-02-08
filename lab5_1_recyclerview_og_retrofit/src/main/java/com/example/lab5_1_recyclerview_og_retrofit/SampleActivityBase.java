package com.example.lab5_1_recyclerview_og_retrofit;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.lab5_1_recyclerview_og_retrofit.logger.Log;
import com.example.lab5_1_recyclerview_og_retrofit.logger.LogWrapper;

public class SampleActivityBase extends FragmentActivity {

    public static final String TAG = "SampleActivityBase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected  void onStart() {
        super.onStart();
        initializeLogging();
    }

    /** Set up targets to receive log data */
    public void initializeLogging() {
        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
        // Wraps Android's native log framework
        LogWrapper logWrapper = new LogWrapper();
        Log.setLogNode(logWrapper);

        Log.i(TAG, "Ready");
    }
}