package com.example.segurados.service;

import com.example.segurados.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsuarioService {

    @GET("/Usuario/{id}")
    Call<Usuario> getUsuario(@Path("id")int id);

    @GET("/Usuario")
    Call<List<Usuario>> getUsuarios();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://.../")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
