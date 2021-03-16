package com.example.lab6_1_trivia_quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuizActivity extends AppCompatActivity {
    private myRepository myRepo = myRepository.getInstance();
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;
    private static final int NUM_PAGES = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        String path = this.getFilesDir().getAbsolutePath()+"/running_quiz.json";
        File yourFile = new File(path);
        if (!yourFile.exists()) {
            myViewModel myViewModel = new ViewModelProvider(this).get(myViewModel.class);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            Map<String, ?> sharedPrefs = sharedPreferences.getAll();
            myViewModel.getQuiz(String.valueOf(sharedPrefs.get("num")), String.valueOf(sharedPrefs.get("category")), String.valueOf(sharedPrefs.get("diff")), String.valueOf(sharedPrefs.get("type"))).observe(this, quizData -> {
                myRepo.writeInternalFile(this, quizData);
            });
        } else {
        ArrayList<Question> test = myRepo.readInternalFile(getApplicationContext());
        }

        viewPager = findViewById(R.id.viewPager);
        viewPager.setPageTransformer(new zDepthPageTransformer());

        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
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
            return NUM_PAGES;
        }
    }

    public void BackHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
