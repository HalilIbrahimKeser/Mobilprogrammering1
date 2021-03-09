package com.example.lab6_1_trivia_quiz.models;

import java.util.List;

public class QuizData {
    private int response_code;
    private List<Question> results;

    public QuizData() {
        this.response_code = response_code;
        this.results = results;
    }

    public int getResponse_code() {
        return response_code;
    }

    public List<Question> getResults() {
        return results;
    }
}
