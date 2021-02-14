package com.example.lab5_3_retfrofitt_recyclerview_fragmenter.repository;

import androidx.lifecycle.MutableLiveData;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Album;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Photo;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class myRepository {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static myRepository repository;
    private static myRepository albumsRepository;
    private static myRepository usersRepository;
    private static myRepository photossRepository;

    public static myRepository getInstance(){
        if (repository == null){
            repository = new myRepository();
        }
        return repository;
    }

    private Retrofit retrofit;
    private jsonPlaceHolderApi repoAPI;
    private MutableLiveData<String> errorMessage;

    private MutableLiveData<List<Album>> albumsData;
    private MutableLiveData<List<User>> userData;
    private MutableLiveData<List<Photo>> photosData;

    private myRepository() {
        errorMessage = new MutableLiveData<>();
        userData = new MutableLiveData<>();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        repoAPI = retrofit.create(jsonPlaceHolderApi.class);
    }

    public MutableLiveData<List<Album>> getAlbumsForUser(int userId) {
        Call<List<Album>> call = repoAPI.getAlbumsForUser((userId));
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<Album> albums = response.body();
                albumsData.setValue(albums);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
        return albumsData;
    }

    public MutableLiveData<List<User>> getUsers() {
        Call<List<User>> call = repoAPI.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<User> users = response.body();
                userData.setValue(users);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
        return userData;
    }

    public MutableLiveData<List<Photo>> getPhotos(int albumId) {
        Call<List<Photo>> call = repoAPI.getPhotos((albumId));
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<Photo> photos = response.body();
                photosData.setValue(photos);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
        return photosData;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
