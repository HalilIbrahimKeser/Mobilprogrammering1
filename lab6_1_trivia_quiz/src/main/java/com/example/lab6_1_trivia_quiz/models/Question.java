package com.example.lab6_1_trivia_quiz.models;

import java.util.List;

public class Question {
    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private List<String> incorrect_answers;

    public Question(String category, String type, String difficulty, String question, String correct_answer, List<String> incorrect_answers) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
    }

    //GETTERS
    public String getCategory() {
        return category;
    }
    public String getType() { return type; }
    public String getDifficulty() {
        return difficulty;
    }
    public String getQuestion() {
        return question;
    }
    public String getCorrect_answer() {
        return correct_answer;
    }

    public List<String> getIncorrect_answers() {
        return incorrect_answers;
    }

    //SETTERS
    public void setCategory(String category) { this.category = category; }
    public void setType(String type) { this.type = type; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public void setQuestion(String question) { this.question = question; }
    public void setCorrect_answer(String correct_answer) { this.correct_answer = correct_answer; }
    public void setIncorrect_answers(List<String> incorrect_answers) { this.incorrect_answers = incorrect_answers; }
}
