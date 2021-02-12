package com.example.lab5_3_retfrofitt_recyclerview_fragmenter.repository;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.Album;
import com.wfamedia.albumsforuser2021.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// NB! Se https://jsonplaceholder.typicode.com/guide/
// Her sender vi en forespørsel mot https://jsonplaceholder.typicode.com/albums?userId=userId   //der userId er et tall, 1,2 osv.
public interface repoAPI {
    @GET("/albums/")
    Call<List<Album>> getAlbumsForUser(@Query("userId") Integer userId);
}
