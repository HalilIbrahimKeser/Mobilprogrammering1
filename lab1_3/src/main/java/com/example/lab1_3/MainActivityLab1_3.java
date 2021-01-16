package com.example.lab1_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivityLab1_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab1_3);
    }

    public void wrightToast(View view) {
        Toast.makeText(this, "Din melding!", Toast.LENGTH_LONG).show();
    }
}