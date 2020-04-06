package com.example.segurados.service;

import com.example.segurados.util.Constant;
import com.example.segurados.model.Pergunta;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface PerguntaService {

    @GET("pergunta/{id}")
    Call<Pergunta> getPergunta(@Path("id") int id);

    @GET("pergunta")
    Call<List<Pergunta>> getPerguntas(@Header("Authorization") String token);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
