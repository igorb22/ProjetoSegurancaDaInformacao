package com.example.segurados.service;

import com.example.segurados.model.Pergunta;
import com.example.segurados.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PerguntaService {

    @GET("/Pergunta/{id}")
    Call<Pergunta> getPergunta(@Path("id") int id);

    @GET("/Pergunta")
    Call<List<Pergunta>> getPerguntas();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://.../")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
