package com.example.segurados.service;

import com.example.segurados.model.Tematica;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TematicaService {

    @GET("tematica/{id}")
    Call<Tematica> getTematica(@Path("id") int id);


    @GET("tematica")
    Call<List<Tematica>> getTematicas();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://italabs-001-site1.ctempurl.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
