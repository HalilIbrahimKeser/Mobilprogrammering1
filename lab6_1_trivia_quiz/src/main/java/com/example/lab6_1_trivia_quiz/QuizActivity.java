package com.example.lab6_1_trivia_quiz;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab6_1_trivia_quiz.fragments.ResultsFragment;
import com.example.lab6_1_trivia_quiz.viewmodel.myViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        myViewModel myViewModel = new ViewModelProvider(this).get(myViewModel.class);
        myViewModel.getQuiz("10","10", "easy","multiple").observe(this, quizData -> {

            List quizDataResults = quizData.getResults();
        });
    }

    public void replaceFragmentWidth(Fragment newFragment, boolean addTobackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (addTobackStack)
            fragmentManager
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .add(R.id.fragment_container, newFragment)
                    .commit();
        else
            fragmentManager
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container, newFragment)
                    .commit();
    }

}
