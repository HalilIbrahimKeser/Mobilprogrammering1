package com.example.lab5_3_retfrofitt_recyclerview_fragmenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.fragments.AlbumsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, AlbumsFragment.newInstance())
                    .commitNow();
        }
    }
}