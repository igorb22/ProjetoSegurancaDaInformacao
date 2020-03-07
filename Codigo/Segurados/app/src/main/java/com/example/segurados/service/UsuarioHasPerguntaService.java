package com.example.segurados.service;

import com.example.segurados.model.UsuarioHasPergunta;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsuarioHasPerguntaService {

    @GET("/UsuarioHasPergunta/{idUsuario}/{idPergunta}")
    Call<UsuarioHasPergunta> getResposta(@Path("idUsuario/idPergunta") int idUsuario, int idPergunta);

    @GET("/UsuarioHasPergunta")
    Call<List<UsuarioHasPergunta>> getRespostas();


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://.../")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
