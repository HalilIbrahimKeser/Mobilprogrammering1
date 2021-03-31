package com.wfamedia.wordroom.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface WordDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

    @Transaction
    @Query("SELECT * FROM wordclass_table")
    LiveData<List<WordClassWords>> getWordClassWords();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertWordClass(WordClass wordClass1);
}