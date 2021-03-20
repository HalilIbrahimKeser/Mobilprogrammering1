package com.example.lab6_1_trivia_quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.lab6_1_trivia_quiz.models.Question;
import com.example.lab6_1_trivia_quiz.viewmodel.myViewModel;

import java.util.ArrayList;

public class CorrectAnswersActivity extends AppCompatActivity {

    private com.example.lab6_1_trivia_quiz.viewmodel.myViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_answers);

    }
}