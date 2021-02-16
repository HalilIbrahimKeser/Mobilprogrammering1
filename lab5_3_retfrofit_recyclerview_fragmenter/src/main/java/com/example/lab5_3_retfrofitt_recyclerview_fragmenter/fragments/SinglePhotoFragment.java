package com.example.lab5_3_retfrofitt_recyclerview_fragmenter.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.MainActivity;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.R;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.adapters.PhotoAdapter;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.adapters.SinglePhotoAdapter;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Photo;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.viewmodel.myViewModel;

import java.util.List;

public class SinglePhotoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private int photoId;
    private String photoUrl;
    private SinglePhotoAdapter singlePhotoAdapter;
    protected List<Photo> photosDataset;
    private myViewModel photoViewModel;

    public SinglePhotoFragment() {}

    public static SinglePhotoFragment newInstance(Integer photoId) {
        SinglePhotoFragment fragment = new SinglePhotoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, photoId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            photoId = getArguments().getInt(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_photo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvDetails = view.findViewById(R.id.tvPhotoName);
        tvDetails.setText(getString(R.string.str_valgtPhotoId) + this.photoId);
        ImageView ivImage = view.findViewById(R.id.ivImage);

        photoViewModel = new ViewModelProvider(requireActivity()).get(myViewModel.class);

        photoViewModel.getPhotos(this.photoId).observe(getViewLifecycleOwner(), allPhotos -> {
            this.photosDataset = allPhotos;

            photoAdapter = new PhotoAdapter(this.photosDataset);


            photoAdapter.setLocalDataSet(allPhotos);
            photoAdapter.notifyDataSetChanged();
        });


    }
}