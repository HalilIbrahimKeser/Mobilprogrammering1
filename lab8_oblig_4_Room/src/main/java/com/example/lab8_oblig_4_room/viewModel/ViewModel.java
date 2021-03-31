package com.example.lab8_oblig_4_room.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lab8_oblig_4_room.model.User;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    public Repository mRepository;

    private final LiveData<List<User>> mAllWords;

    public ViewModel(Application application) {
        super(application);
        mRepository = new Repository(application);
        mAllWords = mRepository.getAllWords();
    }

    public LiveData<List<User>> getAllWords() { return mAllWords; }

    public void insert(User user) { mRepository.insert(user); }
}