package com.example.lab6_1_trivia_quiz.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab6_1_trivia_quiz.MainActivity;
import com.example.lab6_1_trivia_quiz.R;
import com.example.lab6_1_trivia_quiz.adapters.QuizAdapter;
import com.example.lab6_1_trivia_quiz.models.Question;
import com.example.lab6_1_trivia_quiz.models.QuizData;
import com.example.lab6_1_trivia_quiz.viewmodel.myViewModel;

import java.util.List;

public class QuizFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    String amount, category, difficulty, type = null;

    private QuizAdapter quizAdapter;
    protected List<QuizData> quizData;
    private final String fileNameInternal = "running_quiz.json";

    public QuizFragment() {}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public static QuizFragment newInstance(String amount, String category, String difficulty, String type) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, amount);  // TODO riktig??
        args.putString(ARG_PARAM1, category);  // TODO riktig??
        args.putString(ARG_PARAM1, difficulty); // TODO riktig??
        args.putString(ARG_PARAM1, type);   // TODO riktig??
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.amount = getArguments().getString(ARG_PARAM1);
            this.category = getArguments().getString(ARG_PARAM1);
            this.difficulty = getArguments().getString(ARG_PARAM1);
            this.type = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_results, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myViewModel resultsViewModel = new ViewModelProvider(requireActivity()).get(myViewModel.class);

//        resultsViewModel.getQuiz(this.amount, this.category, this.difficulty, this.type).observe(getViewLifecycleOwner(),
//                (List<QuizData> allResults) -> {
//            this.quizData = allResults;
//
//
//            quizAdapter = new QuizAdapter(this.quizData);    //TODO
//            quizAdapter.setClickListener((view1, position) -> {  //TODO
//
//                Log.i("TAG", "Du klikte quiz \"" +
//                        quizAdapter.getItem(position).getResults() +
//                        "\" som ligger i posisjon " + position);
//
//                QuizFragment quizFragment = QuizFragment.newInstance(amount, category, difficulty, type);
//                if (isAdded()) {
//                    ((MainActivity) requireActivity()).replaceFragmentWidth(quizFragment, true);
//                }
//            });
//            quizAdapter.setLocalDataSet(allResults);
//            quizAdapter.notifyDataSetChanged();
//        });

    }
}