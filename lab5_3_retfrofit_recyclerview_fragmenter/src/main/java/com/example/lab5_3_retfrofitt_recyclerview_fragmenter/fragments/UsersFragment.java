package com.example.lab5_3_retfrofitt_recyclerview_fragmenter.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.MainActivity;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.R;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.adapters.UserAdapter;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.User;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.viewmodel.myViewModel;

import java.util.List;
import java.util.Objects;

public class UsersFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private RecyclerView usersRecyclerView;
    private UserAdapter usersAdapter;
    protected List<User> usersDataset;

    public UsersFragment() {}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public static UsersFragment newInstance(int userId) {
        UsersFragment fragment = new UsersFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int userId = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_users, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myViewModel userViewModel = new ViewModelProvider(requireActivity()).get(myViewModel.class);

        userViewModel.getUsers().observe(getViewLifecycleOwner(), allUsers -> {
            this.usersDataset = allUsers;

            usersRecyclerView = view.findViewById(R.id.usersRecyclerView);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            usersRecyclerView.setLayoutManager(layoutManager);
            usersAdapter = new UserAdapter(this.usersDataset);
            usersAdapter.setClickListener((view1, position) -> {
                Log.i("TAG", "Du klikte på bruker \"" + usersAdapter.getItem(position).getName() +
                        "\" som ligger i posisjon " + position);

                long userIdlong = usersDataset.get(position).getId();

                AlbumsFragment albumsFragment = AlbumsFragment.newInstance((int) userIdlong);
                if (isAdded()) {
                    ((MainActivity) requireActivity()).replaceFragmentWidth(albumsFragment, true);
                }
            });
            usersRecyclerView.setAdapter(this.usersAdapter);
            usersAdapter.setLocalDataSet(allUsers);
            usersAdapter.notifyDataSetChanged();
        });
    }
}











