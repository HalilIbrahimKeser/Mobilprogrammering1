package com.example.lab5_3_retfrofitt_recyclerview_fragmenter.repository;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Album;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Photo;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

// NB! Se https://jsonplaceholder.typicode.com/guide/
// Her sender vi en forespørsel mot https://jsonplaceholder.typicode.com/albums?userId=userId   //der userId er et tall, 1,2 osv.
public interface jsonPlaceHolderApi {
    @GET("/users")
    Call<List<User>> getUsers();

    @GET("/albums/")
    Call<List<Album>> getAlbumsForUser(@Query("userId") Integer userId);

    @GET("/photos/")
    Call<List<Photo>> getPhotos(@Query("albumId") Integer albumId);

    @GET("/photo/")
    Call<ResponseBody> getPhoto(@Url String url);

}
