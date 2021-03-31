package com.example.lab8_oblig_4_room.db;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.lab8_oblig_4_room.model.User;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getUsers();
}