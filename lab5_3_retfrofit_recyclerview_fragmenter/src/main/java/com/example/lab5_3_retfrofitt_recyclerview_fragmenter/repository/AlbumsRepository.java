package com.example.lab5_3_retfrofitt_recyclerview_fragmenter.repository;

import androidx.lifecycle.MutableLiveData;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Album;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumsRepository {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static AlbumsRepository albumsRepository;
    public static AlbumsRepository getInstance(){
        if (albumsRepository == null){
            albumsRepository = new AlbumsRepository();
        }
        return albumsRepository;
    }

    private Retrofit retrofit;
    private repoAPI repoAPI;
    private MutableLiveData<String> errorMessage;
    private MutableLiveData<List<Album>> albumsData;

    private AlbumsRepository() {
        errorMessage = new MutableLiveData<>();
        albumsData = new MutableLiveData<>();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        repoAPI = retrofit.create(repoAPI.class);
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

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
