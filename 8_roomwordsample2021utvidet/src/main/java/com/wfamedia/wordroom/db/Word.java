package com.wfamedia.wordroom.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "word_table",
        foreignKeys = {
                @ForeignKey(entity = WordClass.class, parentColumns = "id", childColumns = "fk_wordClassId", onDelete = ForeignKey.CASCADE)
        }
)
public class Word {
    @PrimaryKey (autoGenerate = true)
    public long id;
    @ColumnInfo(name = "word")
    private String mWord;
    @ColumnInfo(name = "fk_wordClassId", index = true)
    public long fk_wordClassId;

    public Word(@NonNull String mWord, @NonNull long fk_wordClassId) {
        this.mWord = mWord;
        this.fk_wordClassId = fk_wordClassId;
    }

    public String getWord(){
        return this.mWord;
    }
}