package com.example.segurados.service;

import com.example.segurados.util.Constant;
import com.example.segurados.model.PontosUsuarioViewModel;
import com.example.segurados.model.RankingViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface UsuarioEstatisticaService {

    @GET("estatistica/{idUsuario}")
    Call<List<PontosUsuarioViewModel>> getEstatistica(@Path("idUsuario") int idUsuario, @Header("Authorization") String token);

    @GET("estatistica")
    Call<List<RankingViewModel>> getRanking(@Header("Authorization") String token);


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
