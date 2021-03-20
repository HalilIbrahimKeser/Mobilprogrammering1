package com.example.lab6_1_trivia_quiz;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class CorrectAnswersActivity extends AppCompatActivity {
    private int correctAnswersCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correct_answers);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        int defaultValue = getResources().getInteger(R.integer.saved_correctanswer_default);
        correctAnswersCount = sharedPref.getInt(getString(R.string.saved_correctanswer), defaultValue);

        TextView tvResultat = findViewById(R.id.tvResultat);

        tvResultat.setText(String.valueOf(correctAnswersCount));

}
}
