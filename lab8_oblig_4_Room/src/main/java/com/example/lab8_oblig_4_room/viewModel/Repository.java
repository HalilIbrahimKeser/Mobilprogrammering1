package com.example.lab8_oblig_4_room.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.lab8_oblig_4_room.db.Dao;
import com.example.lab8_oblig_4_room.db.RoomDatabase;
import com.example.lab8_oblig_4_room.model.User;

import java.util.List;

class Repository {

    private Dao mDao;
    private LiveData<List<User>> mAllWords;

    Repository(Application application) {
        RoomDatabase db = RoomDatabase.getDatabase(application);
        mDao = db.Dao();
        mAllWords = (LiveData<List<User>>) mDao.getUsers();
    }

    LiveData<List<User>> getAllWords() {
        return mAllWords;
    }


    void insert(User user) {
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            mDao.insert(user);
        });
    }
}