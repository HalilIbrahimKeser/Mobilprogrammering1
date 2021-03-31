package com.wfamedia.wordroom.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wordclass_table")
public class WordClass {
    @PrimaryKey (autoGenerate = true)
    public long id;
    public String name;
    public String description;

    public WordClass(@NonNull String name, String description) {
        this.name = name;
        this.description = description;
    }
}