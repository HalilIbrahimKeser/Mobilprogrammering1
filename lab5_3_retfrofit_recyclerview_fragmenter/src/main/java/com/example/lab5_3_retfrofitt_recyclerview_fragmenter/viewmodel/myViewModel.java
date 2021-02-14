package com.example.lab5_3_retfrofitt_recyclerview_fragmenter.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Album;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Photo;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.User;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.repository.myRepository;

import java.util.List;

// ViewModel:
public class myViewModel extends ViewModel {
    private final myRepository albumsRepository;
    private final myRepository usersRepository;
    private final myRepository photosRepository;

    public myViewModel() {
        albumsRepository = myRepository.getInstance();
        usersRepository = myRepository.getInstance();
        photosRepository = myRepository.getInstance();
    }

    public MutableLiveData<List<User>> getUsers() {
        return usersRepository.getUsers();
    }

    public MutableLiveData<List<Album>> getAlbumsForUser(int userId) {
        return albumsRepository.getAlbumsForUser(userId);
    }

    public MutableLiveData<List<Photo>> getPhotos(int albumId) {
        return photosRepository.getPhotos(albumId);
    }

    public MutableLiveData<String> getErrorMessage() {
        return albumsRepository.getErrorMessage();
    }
}
