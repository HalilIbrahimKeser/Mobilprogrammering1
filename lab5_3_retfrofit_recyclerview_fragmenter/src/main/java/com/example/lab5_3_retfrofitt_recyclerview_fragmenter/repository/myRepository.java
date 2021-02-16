package com.example.lab5_3_retfrofitt_recyclerview_fragmenter.repository;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.R;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Album;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Photo;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class myRepository {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static myRepository repository;
//    private static myRepository albumsRepository;
//    private static myRepository usersRepository;
//    private static myRepository photossRepository;

    public static myRepository getInstance(){
        if (repository == null){
            repository = new myRepository();
        }
        return repository;
    }

    private final jsonPlaceHolderApi repoAPI;
    private final MutableLiveData<String> errorMessage;

    private final MutableLiveData<List<Album>> albumsData;
    private final MutableLiveData<List<User>> userData;
    private final MutableLiveData<List<Photo>> photosData;
    private final MutableLiveData<List<Photo>> photo;

    private myRepository() {
        errorMessage = new MutableLiveData<>();
        userData = new MutableLiveData<>();
        albumsData = new MutableLiveData<>();
        photosData = new MutableLiveData<>();
        photo = new MutableLiveData<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        repoAPI = retrofit.create(jsonPlaceHolderApi.class);
    }

    public MutableLiveData<List<Album>> getAlbumsForUser(int userId) {
        Call<List<Album>> call = repoAPI.getAlbumsForUser((userId));
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(@NonNull Call<List<Album>> call, @NonNull Response<List<Album>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<Album> albums = response.body();
                albumsData.setValue(albums);
            }

            @Override
            public void onFailure(@NonNull Call<List<Album>> call, @NonNull Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
        return albumsData;
    }

    public MutableLiveData<List<User>> getUsers() {
        Call<List<User>> call = repoAPI.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<User> users = response.body();
                userData.setValue(users);
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
        return userData;
    }

    public MutableLiveData<List<Photo>> getPhotos(int albumId) {
        Call<List<Photo>> call = repoAPI.getPhotos((albumId));
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Photo>> call, @NonNull Response<List<Photo>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<Photo> photos = response.body();
                photosData.setValue(photos);
//                photo =
            }

            @Override
            public void onFailure(@NonNull Call<List<Photo>> call, @NonNull Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
        return photosData;
    }

    public MutableLiveData<ResponseBody> getPhoto(String url) {
        Call<ResponseBody> call = repoAPI.getPhoto(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                assert response.body() != null;
                Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
//                ImageView ivImage = view.findViewById(R.id.ivImage);
//                ivImage.setImageBitmap(bmp);
//
//                photo.setValue(bmp);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
        return null;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
