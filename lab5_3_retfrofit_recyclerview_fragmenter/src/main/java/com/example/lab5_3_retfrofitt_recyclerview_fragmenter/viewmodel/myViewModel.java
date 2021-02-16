package com.example.lab5_3_retfrofitt_recyclerview_fragmenter.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Album;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Photo;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.User;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.repository.myRepository;

import java.util.List;

import okhttp3.ResponseBody;

public class myViewModel extends ViewModel {
    private final myRepository myRepository;

    public myViewModel() {
        myRepository = com.example.lab5_3_retfrofitt_recyclerview_fragmenter.repository.myRepository.getInstance();
    }

    public MutableLiveData<List<User>> getUsers() {
        return myRepository.getUsers();
    }

    public MutableLiveData<List<Album>> getAlbumsForUser(int userId) {
        return myRepository.getAlbumsForUser(userId);
    }

    public MutableLiveData<List<Photo>> getPhotos(int albumId) {
        return myRepository.getPhotos(albumId);
    }

    public MutableLiveData<ResponseBody> getPhoto(String url) {
        return myRepository.getPhoto(url);
    }

    public MutableLiveData<String> getErrorMessage() {
        return myRepository.getErrorMessage();
    }
}
