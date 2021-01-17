package com.example.lab1_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {
    public static final String MY_DATETIME_FORMAT = "dd.MM.yyyy hh:mm:ss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab1_4);

        TextView etFodselsar = (TextView)findViewById(R.id.etFodselsar);
        int fodselsarInt = etFodselsar.getContext().getResources().getInteger(R.integer.fodselsar);

        String fodselsarString = String.valueOf(fodselsarInt);
        etFodselsar.setText(fodselsarString);

        int nyFodselsarInt = Integer.parseInt(fodselsarString);
        int nyttBeregnetFodselsarInt = nyFodselsarInt + 10;
        String nyfodselsarString = String.valueOf(nyttBeregnetFodselsarInt);
        etFodselsar.setText(nyfodselsarString);

        Calendar calendarNow = Calendar.getInstance();
        String dateNow = new SimpleDateFormat(MY_DATETIME_FORMAT).format(calendarNow.getTime());

        TextView tvResult = (TextView)findViewById(R.id.tvResult);
        String todaysDate = tvResult.getContext().getResources().getString(R.string.dagens_dato_er);
        tvResult.setText(todaysDate + "\n" + dateNow);
    }
}