package com.example.segurados.service;

import com.example.segurados.constant.Constant;
import com.example.segurados.model.RankingViewModel;
import com.example.segurados.model.UsuarioViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsuarioEstatisticaService {

    @GET("estatistica/{idUsuario}")
    Call<List<UsuarioViewModel>> getEstatistica(@Path("idUsuario") int idUsuario);

    @GET("estatistica")
    Call<List<RankingViewModel>> getRanking();


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
