package com.wfamedia.wordroom.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.wfamedia.wordroom.db.Word;
import com.wfamedia.wordroom.db.WordClassWords;
import com.wfamedia.wordroom.db.WordDao;
import com.wfamedia.wordroom.db.WordRoomDatabase;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;
    private LiveData<List<WordClassWords>> mAllWordClassWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
        mAllWordClassWords = mWordDao.getWordClassWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public LiveData<List<WordClassWords>> getAllWordClassWords() {
        return mAllWordClassWords;
    }

    public void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.insert(word);
        });
        //new insertAsyncTask(mWordDao).execute(word);
    }

    /*
    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    */
}
