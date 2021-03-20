package com.example.lab6_1_trivia_quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.lab6_1_trivia_quiz.fragments.QuizActivitySlideFragment;
import com.example.lab6_1_trivia_quiz.models.Question;
import com.example.lab6_1_trivia_quiz.repository.myRepository;
import com.example.lab6_1_trivia_quiz.viewmodel.myViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class QuizActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private myRepository myRepo = myRepository.getInstance();
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;
    private int numPages = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        myViewModel myViewModel = new ViewModelProvider(this).get(myViewModel.class);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation1);
        bottomNav.setOnNavigationItemSelectedListener(this);

        String path = this.getFilesDir().getAbsolutePath()+"/running_quiz.json";
        File yourFile = new File(path);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Map<String, ?> sharedPrefs = sharedPreferences.getAll();
        String temp = String.valueOf(sharedPrefs.get("num"));
        numPages = Integer.parseInt(temp);
        if (!yourFile.exists()) {
            myViewModel.getQuiz(String.valueOf(sharedPrefs.get("num")), String.valueOf(sharedPrefs.get("category")),
                    String.valueOf(sharedPrefs.get("diff")), String.valueOf(sharedPrefs.get("type"))).observe(this, quizData -> {
                myRepo.writeInternalFile(this, quizData);
            });
        } else {
            myRepo.readInternalFile(getApplicationContext());
        }

        viewPager = findViewById(R.id.viewPager);
        viewPager.setPageTransformer(new zDepthPageTransformer());

        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_menu, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.hjem:
                onBackPressed();
                break;
            case R.id.fasit:
                Intent intent = new Intent(this, CorrectAnswersActivity.class);
                startActivity(intent);
                break;
        }
        return false;
    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            return QuizActivitySlideFragment.newInstance(position);
        }

        @Override
        public int getItemCount() {
            return numPages;
        }
    }
}
