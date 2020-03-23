package com.example.segurados.service;

import com.example.segurados.util.Constant;
import com.example.segurados.model.Tematica;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface TematicaService {

    @GET("tematica/{id}")
    Call<Tematica> getTematica(@Path("id") int id);


    @GET("tematica")
    Call<List<Tematica>> getTematicas(@Header("Authorization") String token);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Multipart
    @POST("tematica/add")
    void register(@Part("titulo") String titulo,
                  @Part("descricao") String descricao,
                  Callback<Tematica> response);
}
