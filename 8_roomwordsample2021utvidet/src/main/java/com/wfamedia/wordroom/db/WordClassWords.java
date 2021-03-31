package com.wfamedia.wordroom.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class WordClassWords {
    @Embedded
    WordClass wordClass;

    @Relation(parentColumn = "id", entityColumn = "fk_wordClassId")
    public List<Word> wordsInWordClass;

}
