package com.example.lab6_1_trivia_quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.lab6_1_trivia_quiz.models.Question;
import com.example.lab6_1_trivia_quiz.repository.myRepository;
import com.example.lab6_1_trivia_quiz.viewmodel.myViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuizActivity extends AppCompatActivity{
    private myRepository myRepo;
    Map<String, ?> sharePrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Map<String, ?> sharedPrefs = sharedPreferences.getAll();
                myViewModel myViewModel = new ViewModelProvider(this).get(myViewModel.class);
        myViewModel.getQuiz(String.valueOf(sharedPrefs.get("num")), String.valueOf(sharedPrefs.get("category")), String.valueOf(sharedPrefs.get("diff")), String.valueOf(sharedPrefs.get("type"))).observe(this, quizData -> {
            //List<Question> questions = quizData.getResults();
        });

        String path = this.getFilesDir().getAbsolutePath()+"/running_quiz.json";
        File yourFile = new File(path);
        if (yourFile.exists()) {
            myRepo.readInternalFile(getApplicationContext());
        } else {
            //myRepo.writeInternalFile(getApplicationContext(), (ArrayList<Question>) quizDataResults);
        }
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
