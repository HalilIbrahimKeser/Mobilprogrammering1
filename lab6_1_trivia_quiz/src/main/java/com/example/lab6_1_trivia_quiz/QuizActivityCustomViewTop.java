package com.example.lab6_1_trivia_quiz;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class QuizActivityCustomViewTop extends LinearLayout {
    private TextView tvQuestionNumber, tvQuestionNumSlashNum;

    public QuizActivityCustomViewTop(Context context) {
        super(context);
        init();
    }

    public QuizActivityCustomViewTop(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuizActivityCustomViewTop(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public QuizActivityCustomViewTop(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.quiz_screen_top, this, true);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvQuestionNumSlashNum = findViewById(R.id.tvQuestionNumSlashNum);
        tvQuestionNumber.setText("1");
        tvQuestionNumSlashNum.setText("Spørsmål 1/10");
    }
}
