package com.example.lab5_3_retfrofitt_recyclerview_fragmenter.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.R;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.adapters.PhotoAdapter;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.adapters.SinglePhotoAdapter;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Photo;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.viewmodel.myViewModel;

import java.util.List;

public class SinglePhotoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String photoUrl;
    protected List<Photo> photosDataset;

    public SinglePhotoFragment() {}

    public static SinglePhotoFragment newInstance(Integer photoId, String photoUrl) {
        SinglePhotoFragment fragment = new SinglePhotoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, photoId);
        args.putString(ARG_PARAM2, photoUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int photoId = getArguments().getInt(ARG_PARAM1);
            photoUrl = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.single_photo_row_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView ivImage = view.findViewById(R.id.ivImage);
        myViewModel photoViewModel = new ViewModelProvider(requireActivity()).get(myViewModel.class);
        PhotoAdapter photoAdapter = new PhotoAdapter(this.photosDataset);

        GlideUrl url = new GlideUrl(photoUrl, new LazyHeaders.Builder()
                .addHeader("User-Agent", "android")
                .build());
        Glide.with(getContext())
                .load(url)
                .into(ivImage);
    }
}