package com.example.lab6_1_trivia_quiz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab6_1_trivia_quiz.R;
import com.example.lab6_1_trivia_quiz.models.Question;
import com.example.lab6_1_trivia_quiz.viewmodel.myViewModel;

import java.util.ArrayList;

public class CorrectAnswerFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private int position;
    String amount, category, difficulty, type = null;
    private com.example.lab6_1_trivia_quiz.viewmodel.myViewModel myViewModel;
    protected ArrayList<Question> quizData;
    private int correctAnswersCount = 0;

    public CorrectAnswerFragment() {
    }

    public static CorrectAnswerFragment newInstance(int position) {
        CorrectAnswerFragment fragment = new CorrectAnswerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            this.position = getArguments().getInt(ARG_PARAM1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_correct_answers, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myViewModel = new ViewModelProvider(requireActivity()).get(myViewModel.class);
        myViewModel.getQuiz(amount, category, difficulty, type).observe(getViewLifecycleOwner(), (ArrayList<Question> AllQuiz) -> {
            this.quizData = AllQuiz;

            int sumQuestions = quizData.size();

            StringBuilder listOfCorrectAnswers = new StringBuilder();
            for (int i = 0; i < sumQuestions; i++) {
                String correctAnswers = quizData.get(i).getCorrect_answer();
                listOfCorrectAnswers.append("Riktig svar på spørsmål nr ").append(i+1).append(":\t").append(correctAnswers).append("\n").append("\n");
            }

            TextView tvCorrectAnswersList = view.findViewById(R.id.tvCorrectAnswersList);
            tvCorrectAnswersList.setText(listOfCorrectAnswers);

        });
    }

}