package com.example.segurados.service;

import com.example.segurados.util.Constant;
import com.example.segurados.model.UsuarioHasPergunta;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioHasPerguntaService {

    @GET("usuariohaspergunta/{idUsuario}")
    Call<List<UsuarioHasPergunta>> getRespostasUsuario(@Path("idUsuario") int idUsuario, @Header("Authorization") String token);

    @GET("usuariohaspergunta")
    Call<List<UsuarioHasPergunta>> getRespostas();

    @POST("usuariohaspergunta/create")
    @FormUrlEncoded
    Call<Response> createUsuarioHasPergunta(@Body UsuarioHasPergunta usuarioHasPergunta);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
