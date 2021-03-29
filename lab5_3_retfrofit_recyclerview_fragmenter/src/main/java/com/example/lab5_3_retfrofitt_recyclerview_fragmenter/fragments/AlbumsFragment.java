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
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.databinding.FragmentAlbumsBinding;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Album;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.viewmodel.myViewModel;

import java.util.List;

//ENDRINGER GJORT; LA INN viewbinding

public class AlbumsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private int userId;
    private RecyclerView albumsRecyclerView;
    private AlbumAdapter albumAdapter;
    protected List<Album> myDataset;
    private FragmentAlbumsBinding fragmentAlbumsBinding;


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
        fragmentAlbumsBinding = FragmentAlbumsBinding.inflate(inflater, container, false);
        return fragmentAlbumsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myViewModel albumsViewModel = new ViewModelProvider(requireActivity()).get(myViewModel.class);

        albumsViewModel.getAlbumsForUser(this.userId).observe(getViewLifecycleOwner(), (List<Album> allAlbums) -> {
            this.myDataset = allAlbums;

            albumsRecyclerView = fragmentAlbumsBinding.albumsRecyclerView;
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            albumsRecyclerView.setLayoutManager(layoutManager);

            albumAdapter = new AlbumAdapter(this.myDataset);
            albumAdapter.setClickListener((view1, position) -> {

                Log.i("TAG", "Du klikte album \"" +
                        albumAdapter.getItem(position).getTitle() +
                        "\" som ligger i posisjon " + position);

                long albumIdlong = myDataset.get(position).getId();

                PhotoFragment photoFragment = PhotoFragment.newInstance((int) albumIdlong);
                if (isAdded()) {
                    ((MainActivity) requireActivity()).replaceFragmentWidth(photoFragment, true);
                }
            });
            albumsRecyclerView.setAdapter(this.albumAdapter);
            albumAdapter.setLocalDataSet(allAlbums);
            albumAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentAlbumsBinding = null;
    }
}