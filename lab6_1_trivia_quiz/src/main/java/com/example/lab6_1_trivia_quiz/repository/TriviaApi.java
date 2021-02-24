package com.example.lab6_1_trivia_quiz.repository;

import com.example.lab6_1_trivia_quiz.models.Results;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TriviaApi {
    @GET("/api.php")
    Call<List<Results>> getAllResults();

    @GET("/api.php?")
    Call<List<Results>> getResults(
            @Query("amount") Integer amount,
            @Query("category") Integer category,
            @Query("difficulty") String difficulty,
            @Query("type") String type);
}
