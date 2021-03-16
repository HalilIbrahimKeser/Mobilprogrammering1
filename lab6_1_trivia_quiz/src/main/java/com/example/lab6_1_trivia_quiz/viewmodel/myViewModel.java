package com.example.lab6_1_trivia_quiz.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab6_1_trivia_quiz.models.Question;
import com.example.lab6_1_trivia_quiz.models.QuizData;
import com.example.lab6_1_trivia_quiz.repository.myRepository;

import java.util.ArrayList;
import java.util.List;

public class myViewModel extends ViewModel {
    private final myRepository myRepository;

    private MutableLiveData<ArrayList<Question>> quizArray;

    public myViewModel() {
        myRepository = com.example.lab6_1_trivia_quiz.repository.myRepository.getInstance();
    }

    public MutableLiveData<ArrayList<Question>> getQuiz(String amount, String category, String difficulty, String type) {
        return myRepository.downloadQuiz(amount, category, difficulty, type);
    }

    public MutableLiveData<ArrayList<Question>> getQuizArray() {
        return quizArray;
    }

    public void setQuizArray(MutableLiveData<ArrayList<Question>> quizArray) {
        this.quizArray = quizArray;
    }

    public MutableLiveData<String> getErrorMessage() {
        return myRepository.getErrorMessage();
    }
}
