package com.example.lab6_1_trivia_quiz;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;


public class CorrectAnswersActivity extends AppCompatActivity {
    private int correctAnswersCount;
    private int correctAnswersSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correct_answers);

        SharedPreferences sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        //int defaultValue = getResources().getInteger(R.integer.saved_correctanswer_default);
        //correctAnswersCount = sharedPref.getInt(getString(R.string.saved_correctanswer), defaultValue);
        correctAnswersCount = sharedPref.getInt("count", 0);
        correctAnswersSize = sharedPref.getInt("countSize", 0);

        TextView tvResultat = findViewById(R.id.tvResultat);
        TextView tvKarakter = findViewById(R.id.tvKarakter);

        String resultat = "Resultat: " + correctAnswersCount + " / " + correctAnswersSize;

        String karakter = "Karakter: " + calculateResult();

        tvResultat.setText(resultat);
        tvKarakter.setText(karakter);


    }
    public char calculateResult () {
        char ch = 'F';
        int prosent = ((correctAnswersSize / 100) * correctAnswersCount) * 100;

        if (prosent < 40 ) {
            ch = 'F';
        }else if (prosent >= 41 && prosent <= 52 ) {
            ch = 'E';
        }else if (prosent >= 53 && prosent <= 64 ) {
            ch = 'D';
        }else if (prosent >= 65 && prosent <= 76 ) {
            ch = 'C';
        }else if (prosent >= 77 && prosent <= 88 ) {
            ch = 'B';
        }else if (prosent >= 89 && prosent <= 100 ) {
            ch = 'A';
        }else {
            ch = 'F';
        }return ch;
    }
}
