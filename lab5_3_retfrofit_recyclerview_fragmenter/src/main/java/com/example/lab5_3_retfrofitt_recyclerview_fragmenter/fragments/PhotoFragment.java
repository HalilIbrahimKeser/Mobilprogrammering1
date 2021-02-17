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
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.adapters.PhotoAdapter;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Photo;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.viewmodel.myViewModel;

import java.util.List;

public class PhotoFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private int photoId;
    private String photoUrl;
    private RecyclerView photoRecyclerView;
    private PhotoAdapter photoAdapter;
    protected List<Photo> photosDataset;
    private myViewModel photoViewModel;

    public PhotoFragment() {}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    public static PhotoFragment newInstance(int AlbumId) {
        PhotoFragment fragment = new PhotoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, AlbumId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.photoId = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        photoViewModel = new ViewModelProvider(requireActivity()).get(myViewModel.class);

        photoViewModel.getPhotos(this.photoId).observe(getViewLifecycleOwner(), allPhotos -> {
            this.photosDataset = allPhotos;
            //
            photoRecyclerView = view.findViewById(R.id.photosRecyclerView);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            photoRecyclerView.setLayoutManager(layoutManager);
            //
            photoAdapter = new PhotoAdapter(this.photosDataset);
            photoAdapter.setClickListener((view1, position) -> {
                Log.i("TAG", "Du klikte bilde \"" + photoAdapter.getItem(position).getTitle() +
                        "\" som ligger i posisjon " + position);
                Integer photoIdlong = photosDataset.get(position).getId();

                SinglePhotoFragment singlePhotoFragment = SinglePhotoFragment.newInstance(photoIdlong);
                if (isAdded()) {
                    ((MainActivity) requireActivity()).replaceFragmentWidth(singlePhotoFragment, true);
                }
            });
            photoRecyclerView.setAdapter(this.photoAdapter);
            photoAdapter.setLocalDataSet(allPhotos);
            photoAdapter.notifyDataSetChanged();
        });

    }
}