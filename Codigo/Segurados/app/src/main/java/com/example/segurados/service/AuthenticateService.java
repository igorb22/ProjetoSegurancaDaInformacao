package com.example.segurados.service;

import com.example.segurados.model.Usuario;
import com.example.segurados.model.UsuarioViewModel;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthenticateService {

    @GET("authenticate/")
    Call<String> testController();

    @POST("authenticate/login/")
    Call<UsuarioViewModel> authenticate(@Body Usuario model);

    @POST("authenticate/register/")
    Call<UsuarioViewModel> register(@Body Usuario model);


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://italabs-001-site1.ctempurl.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
