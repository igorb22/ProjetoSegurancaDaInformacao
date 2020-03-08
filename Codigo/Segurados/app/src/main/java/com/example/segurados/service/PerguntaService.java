package com.example.segurados.service;

import com.example.segurados.model.Pergunta;
import com.example.segurados.model.Usuario;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PerguntaService {

    @GET("pergunta/{id}")
    Call<Pergunta> getPergunta(@Path("id") int id);

    @GET("pergunta")
    Call<List<Pergunta>> getPerguntas();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://italabs-001-site1.ctempurl.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
