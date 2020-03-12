package com.example.segurados.service;

import com.example.segurados.constant.Constant;
import com.example.segurados.model.UsuarioHasPergunta;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsuarioHasPerguntaService {

    @GET("usuariohaspergunta/{idUsuario}")
    Call<List<UsuarioHasPergunta>> getRespostasUsuario(@Path("idUsuario") int idUsuario);

    @GET("usuariohaspergunta")
    Call<List<UsuarioHasPergunta>> getRespostas();


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
