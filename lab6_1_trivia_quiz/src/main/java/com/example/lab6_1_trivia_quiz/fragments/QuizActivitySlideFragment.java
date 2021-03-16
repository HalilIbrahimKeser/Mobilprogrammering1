package com.example.lab6_1_trivia_quiz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.lab6_1_trivia_quiz.R;
import com.example.lab6_1_trivia_quiz.models.Question;
import com.example.lab6_1_trivia_quiz.viewmodel.myViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivitySlideFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private int position;
    String amount, category, difficulty, type = null;
    private com.example.lab6_1_trivia_quiz.viewmodel.myViewModel myViewModel;
    protected ArrayList<Question> quizData;

    public QuizActivitySlideFragment() {
        // Required empty public constructor
    }

    public static QuizActivitySlideFragment newInstance(int position) {
        QuizActivitySlideFragment fragment = new QuizActivitySlideFragment();
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
        return inflater.inflate(R.layout.fragment_quiz_slide_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myViewModel = new ViewModelProvider(requireActivity()).get(myViewModel.class);
        myViewModel.getQuiz(amount, category, difficulty, type).observe(getViewLifecycleOwner(), (ArrayList<Question> AllQuiz) -> {
            this.quizData = AllQuiz;

            TextView tvQuestion = view.findViewById(R.id.tvQuestion);
            RadioButton rbAnswer1 = view.findViewById(R.id.rbAnswer1);
            RadioButton rbAnswer2 = view.findViewById(R.id.rbAnswer2);
            RadioButton rbAnswer3 = view.findViewById(R.id.rbAnswer3);
            RadioButton rbAnswer4 = view.findViewById(R.id.rbAnswer4);

            List<String> alternatives = new ArrayList<>();
            alternatives.add(quizData.get(position).getCorrect_answer());
            alternatives.add(quizData.get(position).getIncorrect_answers().get(0));
            alternatives.add(quizData.get(position).getIncorrect_answers().get(1));
            alternatives.add(quizData.get(position).getIncorrect_answers().get(2));
            Collections.shuffle(alternatives);

            tvQuestion.setText(quizData.get(position).getQuestion());
            rbAnswer1.setText(alternatives.get(0));
            rbAnswer2.setText(alternatives.get(1));
            rbAnswer3.setText(alternatives.get(2));
            rbAnswer4.setText(alternatives.get(3));
        });
    }
}
