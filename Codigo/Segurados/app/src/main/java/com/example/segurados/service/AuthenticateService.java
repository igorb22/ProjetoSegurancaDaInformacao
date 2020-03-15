package com.example.segurados.service;

import com.example.segurados.constant.Constant;
import com.example.segurados.model.Usuario;
import com.example.segurados.model.UsuarioViewModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthenticateService {

    @GET("authenticate/")
    Call<String> testController();

    @POST("authenticate/login/")
    Call<UsuarioViewModel> authenticate(@Body Usuario model);


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
