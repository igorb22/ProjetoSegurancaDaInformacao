package com.example.segurados.service;

import com.example.segurados.util.Constant;
import com.example.segurados.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface UsuarioService {

    @GET("usuario/{id}")
    Call<Usuario> getUsuario(@Path("id")int id,@Header("Authorization") String token);

    @GET("usuario")
    Call<List<Usuario>> getUsuarios();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
