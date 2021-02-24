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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab6_1_trivia_quiz.MainActivity;
import com.example.lab6_1_trivia_quiz.R;

import com.example.lab6_1_trivia_quiz.models.Results;
import com.example.lab6_1_trivia_quiz.viewmodel.myViewModel;

import java.util.List;

public class ResultsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private int amount;
    private int category;
    private String difficulty;
    private String type;

    private RecyclerView ResultsRecyclerView;
    private ResultsAdapter resultsAdapter;
    protected List<Results> myDataset;

    public ResultsFragment() {}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public static ResultsFragment newInstance(int amount, int category, String difficulty, String type) {
        ResultsFragment fragment = new ResultsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, amount);  // TODO riktig??
        args.putInt(ARG_PARAM1, category);  // TODO riktig??
        args.putString(ARG_PARAM1, difficulty); // TODO riktig??
        args.putString(ARG_PARAM1, type);   // TODO riktig??
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.amount = getArguments().getInt(ARG_PARAM1);
            this.category = getArguments().getInt(ARG_PARAM1);
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

        resultsViewModel.getResults(this.amount, this.category, this.difficulty, this.type).observe(getViewLifecycleOwner(), allResults -> {
            this.myDataset = allResults;

//            albumsRecyclerView = view.findViewById(R.id.albumsRecyclerView);
//            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//            albumsRecyclerView.setLayoutManager(layoutManager);

            resultsAdapter = new ResultsAdapter(this.myDataset);    //TODO
            resultsAdapter.setClickListener((view1, position) -> {  //TODO

//                Log.i("TAG", "Du klikte quiz \"" +
//                        resultsAdapter.getItem(position).getTitle() +
//                        "\" som ligger i posisjon " + position);
//
//                long albumIdlong = myDataset.get(position).getId();

                QuizFragment quizFragment = QuizFragment.newInstance((int) albumIdlong); //TODO
                if (isAdded()) {
                    ((MainActivity) requireActivity()).replaceFragmentWidth(quizFragment, true);
                }
            });
//            albumsRecyclerView.setAdapter(this.albumAdapter);
            resultsAdapter.setLocalDataSet(allResults);
            resultsAdapter.notifyDataSetChanged();
        });

    }
}