package com.example.lab1_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab1_4);

        TextView textView = (TextView)findViewById(R.id.etFodselsar);
        int fodselsarInt = textView.getContext().getResources().getInteger(R.integer.fodselsar);

        String fodselsarString = String.valueOf(fodselsarInt);
        textView.setText(fodselsarString);

        int nyFodselsarInt = Integer.parseInt(textView.toString());
        int nyttBeregnetFodselsarInt = nyFodselsarInt + 10;
    }
}