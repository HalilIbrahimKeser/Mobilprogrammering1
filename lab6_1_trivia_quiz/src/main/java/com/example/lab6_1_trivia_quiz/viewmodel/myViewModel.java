package com.example.lab6_1_trivia_quiz.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.lab6_1_trivia_quiz.models.Results;
import java.util.List;

public class myViewModel extends ViewModel {
    private final com.example.lab6_1_trivia_quiz.repository.myRepository myRepository;

    public myViewModel() {
        myRepository = com.example.lab6_1_trivia_quiz.repository.myRepository.getInstance();
    }

    public MutableLiveData<List<Results>> getResults(int amount, int category, String difficulty, String type) {
        return myRepository.getResults(amount, category, difficulty, type);
    }

    public MutableLiveData<String> getErrorMessage() {
        return myRepository.getErrorMessage();
    }
}
