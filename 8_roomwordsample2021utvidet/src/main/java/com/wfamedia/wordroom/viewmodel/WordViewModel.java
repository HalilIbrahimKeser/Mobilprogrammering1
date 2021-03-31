package com.wfamedia.wordroom.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.wfamedia.wordroom.db.Word;
import com.wfamedia.wordroom.db.WordClassWords;
import com.wfamedia.wordroom.viewmodel.WordRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository mRepository;
    //private LiveData<List<Word>> mAllWords;

    public WordViewModel(Application application) {
        super(application);
        mRepository = new WordRepository(application);
    }

    public LiveData<List<Word>> getAllWords() {
        return mRepository.getAllWords();
    }

    public LiveData<List<WordClassWords>> getAllWordClassWords() {
        return mRepository.getAllWordClassWords();
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }


}
