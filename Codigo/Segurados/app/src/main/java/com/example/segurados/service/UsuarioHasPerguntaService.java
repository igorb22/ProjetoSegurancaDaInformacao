package com.example.segurados.service;

import com.example.segurados.model.UsuarioHasPergunta;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsuarioHasPerguntaService {

    @GET("usuariohaspergunta/{idUsuario}/{idPergunta}")
    Call<UsuarioHasPergunta> getResposta(@Path("idUsuario/idPergunta") int idUsuario, int idPergunta);

    @GET("usuariohaspergunta")
    Call<List<UsuarioHasPergunta>> getRespostas();


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://italabs-001-site1.ctempurl.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
