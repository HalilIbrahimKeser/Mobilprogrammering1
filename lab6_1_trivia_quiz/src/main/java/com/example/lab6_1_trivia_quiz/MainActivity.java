package com.example.lab6_1_trivia_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import com.example.lab6_1_trivia_quiz.fragments.QuizFragment;
import com.example.lab6_1_trivia_quiz.models.Question;
import com.example.lab6_1_trivia_quiz.models.QuizData;
import com.example.lab6_1_trivia_quiz.repository.TriviaApi;
import com.example.lab6_1_trivia_quiz.viewmodel.myViewModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    protected QuizData quizData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar quizToolbar = findViewById(R.id.quizToolbar1);
        setSupportActionBar(quizToolbar);
    }

    public void settingsActivity(MenuItem item) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

//    public void replaceFragmentWidth(QuizFragment quizFragment, boolean b) {
//
//    }
}

//    myViewModel myViewModel = new ViewModelProvider(this).get(myViewModel.class);
//        myViewModel.getQuiz("10","10", "easy","multiple").observe(this, quizData -> {
//                List<Question> quizDataResults = quizData.getResults();
//        StringBuilder string = new StringBuilder();
//        for (int i = 0; i < 10; i++) {
//        string.append(i + 1).append(": ").append(quizDataResults.get(i).getCorrect_answer()).append(", ");
//        }
//        Log.i("test", string + "\n");
//        });