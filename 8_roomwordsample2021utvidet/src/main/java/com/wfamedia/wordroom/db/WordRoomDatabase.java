package com.wfamedia.wordroom.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Word.class, WordClass.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();
    private static volatile WordRoomDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            WordRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                WordDao dao = INSTANCE.wordDao();
                dao.deleteAll();

                WordClass wordClass1 = new WordClass("Substantiv", "Ord man kan sette en, ei eller et foran.");
                long wc1Id = dao.insertWordClass(wordClass1);

                WordClass wordClass2 = new WordClass("Verb", "Ord man kan sette å foran.");
                long wc2Id = dao.insertWordClass(wordClass2);

                Word word1 = new Word("Datamaskin", wc1Id);
                dao.insert(word1);
                Word word2 = new Word("Hus", wc1Id);
                dao.insert(word2);
                Word word3 = new Word("Sykkel", wc1Id);
                dao.insert(word3);

                Word word4 = new Word("Sykle", wc2Id);
                dao.insert(word4);
                Word word5 = new Word("Springe", wc2Id);
                dao.insert(word5);
            });
        }
    };
}