package com.example.lab6_1_trivia_quiz;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class QuestionsView extends LinearLayout {
    private EditText questionNumber;
    private EditText questionNumSlashNum;


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
        layoutInflater.inflate(R.layout.clearable_edit_text, this, true);
        questionNumber = findViewById(R.id.questionNumber);
        clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
    }
}
}
