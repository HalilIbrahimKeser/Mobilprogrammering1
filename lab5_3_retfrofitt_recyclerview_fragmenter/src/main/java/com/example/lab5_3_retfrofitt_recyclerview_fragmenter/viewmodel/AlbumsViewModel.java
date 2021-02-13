package com.example.lab5_3_retfrofitt_recyclerview_fragmenter.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Album;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.repository.AlbumsRepository;

import java.util.List;

// ViewModel:
public class AlbumsViewModel extends ViewModel {
    private AlbumsRepository albumsRepository;

    public AlbumsViewModel() {
        albumsRepository = AlbumsRepository.getInstance();
    }

    public MutableLiveData<List<Album>> getAlbumsForUser(int userId) {
        return albumsRepository.getAlbumsForUser(userId);
    }

    public MutableLiveData<String> getErrorMessage() {
        return albumsRepository.getErrorMessage();
    }
}
