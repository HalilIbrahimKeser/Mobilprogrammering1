package com.example.lab5_3_retfrofitt_recyclerview_fragmenter.fragments;

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

import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.MainActivity;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.R;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.adapters.AlbumAdapter;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Album;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.viewmodel.myViewModel;

import java.util.List;
import java.util.Objects;

public class AlbumsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private int userId;
    private RecyclerView albumsRecyclerView;
    private AlbumAdapter albumAdapter;
    protected List<Album> myDataset;
    private myViewModel albumsViewModel;

    public AlbumsFragment() {}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    public static AlbumsFragment newInstance(int userId) {
        AlbumsFragment fragment = new AlbumsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.userId = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_albums, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        albumsViewModel = new ViewModelProvider(requireActivity()).get(myViewModel.class);

        albumsViewModel.getAlbumsForUser(this.userId).observe(getViewLifecycleOwner(), allAlbums -> {

            this.myDataset = allAlbums;

            albumsRecyclerView = view.findViewById(R.id.albumsRecyclerView);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            albumsRecyclerView.setLayoutManager(layoutManager);
            albumAdapter = new AlbumAdapter(this.myDataset);

            albumAdapter.setClickListener((view1, position) -> {
                Log.i("TAG", "Du klikke album på \"" + albumAdapter.getItem(position).getTitle() +
                        "\" som ligger i posisjon " + position);

                Long albumIdlong = myDataset.get(position).getId();

                PhotoFragment photoFragment = PhotoFragment.newInstance(albumIdlong.intValue());
                if (isAdded()) {
                    ((MainActivity) requireActivity()).replaceFragmentWidth(photoFragment, true);
                }
            });
            albumsRecyclerView.setAdapter(this.albumAdapter);
            albumAdapter.setLocalDataSet(allAlbums);
            albumAdapter.notifyDataSetChanged();
        });

    }
}