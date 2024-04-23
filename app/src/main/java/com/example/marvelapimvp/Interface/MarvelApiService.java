package com.example.marvelapimvp.Interface;

import com.example.marvelapimvp.Models.Root;
import com.example.marvelapimvp.Models.Root2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelApiService {
    @GET("characters")
    Call<Root> obtenerListaMarvel(
            @Query("limit") int limit,
            @Query("offset") int offset,
            @Query("apikey") String apikey,
            @Query("hash") String has,
            @Query("ts") int ts
    );
    @GET("comics")
    Call<Root2> obtenerListaComics(
            @Query("limit") int limit,
            @Query("offset") int offset,
            @Query("apikey") String apikey,
            @Query("hash") String has,
            @Query("ts") int ts
    );
}