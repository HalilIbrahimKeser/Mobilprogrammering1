package com.example.lab5_3_retfrofitt_recyclerview_fragmenter.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.R;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.adapters.AlbumsViewModel;

public class AlbumsFragment extends Fragment {

    private AlbumsViewModel mViewModel;

    public static AlbumsFragment newInstance() {
        return new AlbumsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.albums_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AlbumsViewModel.class);
        // TODO: Use the ViewModel
    }

}