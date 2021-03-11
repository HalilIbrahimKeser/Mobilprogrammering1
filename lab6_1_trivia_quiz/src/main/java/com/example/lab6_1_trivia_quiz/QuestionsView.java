package com.example.lab6_1_trivia_quiz;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.example.lab6_1_trivia_quiz.models.Question;

import androidx.annotation.Nullable;

import com.example.lab6_1_trivia_quiz.models.Question;

public class QuestionsView extends LinearLayout {
    private TextView tvQuestionNumber, tvQuestionNumSlashNum, tvQuestion;
    private RadioButton rbAnswer1, rbAnswer2, rbAnswer3, rbAnswer4;

    public QuestionsView(Context context) {
        super(context);
        init();
    }

    public QuestionsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuestionsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public QuestionsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        //Merk bruk av LayoutInflater:
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.fragment_quiz_slide_screen, this, true);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvQuestionNumSlashNum = findViewById(R.id.tvQuestionNumSlashNum);
        tvQuestion = findViewById(R.id.tvQuestion);
        rbAnswer1 = findViewById(R.id.rbAnswer1);
        rbAnswer2 = findViewById(R.id.rbAnswer2);
        rbAnswer3 = findViewById(R.id.rbAnswer3);
        rbAnswer4 = findViewById(R.id.rbAnswer4);

        tvQuestionNumber.setOnClickListener(v -> tvQuestionNumber.setText(""));
        tvQuestionNumSlashNum.setOnClickListener(v -> tvQuestionNumSlashNum.setText(""));
        tvQuestion.setOnClickListener(v -> tvQuestion.setText(""));
        rbAnswer1.setOnClickListener(v -> rbAnswer1.setText(""));
        rbAnswer2.setOnClickListener(v -> rbAnswer2.setText(""));
        rbAnswer3.setOnClickListener(v -> rbAnswer3.setText(""));
        rbAnswer4.setOnClickListener(v -> rbAnswer4.setText(""));
    }
}
