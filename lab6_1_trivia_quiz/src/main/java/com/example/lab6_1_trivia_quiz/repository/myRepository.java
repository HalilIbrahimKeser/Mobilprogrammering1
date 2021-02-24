package com.example.lab6_1_trivia_quiz.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.example.lab6_1_trivia_quiz.models.Results;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class myRepository {
    private static final String BASE_URL = "https://opentdb.com/api.php";
    private static myRepository repository;

    public static myRepository getInstance(){
        if (repository == null){
            repository = new myRepository();
        }
        return repository;
    }

    private final TriviaApi repoAPI;
    private final MutableLiveData<String> errorMessage;
    private final MutableLiveData<List<Results>> resultsData;

    private myRepository() {
        errorMessage = new MutableLiveData<>();
        resultsData = new MutableLiveData<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        repoAPI = retrofit.create(TriviaApi.class);
    }

    public MutableLiveData<List<Results>> getResults(int amount, int category, String difficulty, String type) {
        Call<List<Results>> call = repoAPI.getResult((amount), (category), (difficulty), (type));
        call.enqueue(new Callback<List<Results>>() {
            @Override
            public void onResponse(@NonNull Call<List<Results>> call, @NonNull Response<List<Results>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<Results> results = response.body();
                resultsData.setValue(results);
            }

            @Override
            public void onFailure(@NonNull Call<List<Results>> call, @NonNull Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
        return resultsData;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
