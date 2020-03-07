package com.example.segurados.service;

import com.example.segurados.model.Tematica;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TematicaService {

    @GET("/Tematica/{id}")
    Call<Tematica> getTematica(@Path("id") int id);


    @GET("/Tematica")
    Call<List<Tematica>> getTematicas();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://.../")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
