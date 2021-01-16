package com.example.lab1_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;

public class MainActivity_Lab1_2 extends AppCompatActivity {
    public static final String MY_DATE_FORMAT = "dd.MM.yyyy";
    public static final String MY_DATETIME_FORMAT = "dd.MM.yyyy hh:mm:ss";
    private TextView tvHeader;
    private java.util.Calendar calendarGiven=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1_2);

        Calendar calendarNow = Calendar.getInstance();
        String dateNow = new SimpleDateFormat(MY_DATE_FORMAT).format(calendarNow.getTime());

        tvHeader = this.findViewById(R.id.tvHeader);
        tvHeader.setText("Beregner tidsdifferanse fra " + dateNow + " til ...");

        // Henter referanse til calendarView:
        CalendarView calendarView = findViewById(R.id.calendarView2);
        // Setter listener:
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = dayOfMonth + "." + (month + 1) + "." + year;
                try {
                    java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(MY_DATE_FORMAT);
                    calendarGiven = java.util.Calendar.getInstance();
                    calendarGiven.setTime(simpleDateFormat.parse(selectedDate));
                    Toast.makeText(MainActivity_Lab1_2.this, "Du valgte " + selectedDate, Toast.LENGTH_SHORT).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void beregnDato(View view) {
        Calendar calendarNow = Calendar.getInstance();
        String dateNow = new SimpleDateFormat(MY_DATETIME_FORMAT).format(calendarNow.getTime());

        tvHeader = this.findViewById(R.id.tvHeader);
        tvHeader.setText("Beregner tidsdifferanse fra " + dateNow + " til ...");

        EditText etDateGiven = findViewById(R.id.etDateGiven);
        String dateGiven = etDateGiven.getText().toString();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MY_DATE_FORMAT);
        try {
            Calendar calendarGiven = Calendar.getInstance();
            calendarGiven.setTime(simpleDateFormat.parse(dateGiven));

            long milsecs1= calendarNow.getTimeInMillis();
            long milsecs2 = calendarGiven.getTimeInMillis();

            long diffMilliseconds = milsecs2 - milsecs1;

            long diffSekunder = Math.abs(diffMilliseconds / 1000);

            long days = diffSekunder / (24*3600);               //Antall hele dager mellom gitte datoer.
            long restSecondsDays = diffSekunder % (24*3600);    //Restsekunder etter heltallsdivisjon.
            long hours = restSecondsDays / 3600;                //Antall hele dager timer av restSecondsDays.
            long restSeconds = restSecondsDays % 3600;          //Restsekunder etter heltallsdivisjon.
            long minutes = restSeconds / 60;                    //Antall hele minutter i restSeconds.
            long seconds = restSeconds % 60;                    //osv.

            TextView tvResult = this.findViewById(R.id.textViewResult);
            tvResult.setText(days + "d " + hours + "t " + minutes + "m");

            if (diffMilliseconds < 0)
                tvResult.setBackgroundColor(Color.RED);
            else
                tvResult.setBackgroundColor(Color.GREEN);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}