package com.example.lab6_1_trivia_quiz.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.lab6_1_trivia_quiz.models.Question;
import com.example.lab6_1_trivia_quiz.models.QuizData;

import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class myRepository {
    private static myRepository repository;
    private final String fileNameInternal = "running_quiz.json";

    public static myRepository getInstance(){
        if (repository == null){
            repository = new myRepository();
        }
        return repository;
    }

    private final TriviaApi triviaApi;
    private final MutableLiveData<QuizData> quizData;
    private final MutableLiveData<String> errorMessage;

    private myRepository() {
        errorMessage = new MutableLiveData<>();
        quizData = new MutableLiveData<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://opentdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        triviaApi = retrofit.create(TriviaApi.class);
    }

    public MutableLiveData<QuizData> downloadQuiz(String amount, String category, String difficulty, String type) {
        // Legger url-parametre i en HashMap:
        Map<String, String> urlArguments = new HashMap<>();
        urlArguments.put("amount", amount);
        urlArguments.put("category", category);
        urlArguments.put("difficulty", difficulty);
        urlArguments.put("type", type);
        Call<QuizData> call = triviaApi.downloadQuiz(urlArguments);
        call.enqueue(new Callback<QuizData>() {
            @Override
            public void onResponse(Call<QuizData> call, Response<QuizData> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                QuizData data = response.body();
                quizData.setValue(data);
                writeToFile(quizData);

                List<Question> results = null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("response_code", response_code);
                jsonObject.put("results", results);
            }
            @Override
            public void onFailure(Call<QuizData> call, Throwable t) {
            }
        });
        return quizData;
    }

    public void writeToFile(MutableLiveData<QuizData> quizData) {
        // Convert JsonObject to String Format
        String questionsString = quizData.toString();
        FileOutputStream fileOutputStream = null;
        String filesDir = getFilesDir().toString();
        try {
            fileOutputStream = this.openFileOutput(fileNameInternal, Context.MODE_PRIVATE);
            fileOutputStream.write(questionsString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
