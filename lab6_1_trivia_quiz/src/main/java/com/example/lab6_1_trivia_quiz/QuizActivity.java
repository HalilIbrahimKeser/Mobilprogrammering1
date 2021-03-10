package com.example.lab6_1_trivia_quiz;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.lab6_1_trivia_quiz.models.Question;
import com.example.lab6_1_trivia_quiz.repository.myRepository;
import com.example.lab6_1_trivia_quiz.viewmodel.myViewModel;

import java.io.File;
import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private myRepository myRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        myViewModel myViewModel = new ViewModelProvider(this).get(myViewModel.class);
        myViewModel.getQuiz("10","10", "easy","multiple").observe(this, quizData -> {
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
