package com.example.segurados.view.ui.perfil;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.segurados.R;
import com.example.segurados.model.Pergunta;
import com.example.segurados.model.Usuario;
import com.example.segurados.service.PerguntaService;
import com.example.segurados.service.UsuarioService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {
    private Button requisicao;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_perfil, container, false);


        requisicao = v.findViewById(R.id.btnRequisicao);

        requisicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                PerguntaService pergunta = PerguntaService.retrofit.create(PerguntaService.class);
                final Call<Pergunta> call = pergunta.getPergunta(1);


                call.enqueue(new Callback<Pergunta>() {
                    @Override
                    public void onResponse(Call<Pergunta> call, Response<Pergunta> response) {
                        int code = response.code();

                        if(code == 200){
                            Pergunta usuarios = response.body();

                            Toast.makeText(getContext(),"NOME: "+usuarios.getAlternativa1()
                                            +"EMAIL: "+usuarios.getAlternativa2()
                                            +"ID: "+usuarios.getAlternativa3()
                                            +"PERFIL: "+usuarios.getAlternativa4(),
                                    Toast.LENGTH_LONG).show();

                        }else{

                            Toast.makeText(getContext(),"Falhou",
                                    Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Pergunta> call, Throwable t) {

                    }


                });

                 */
            }
        });

        return v;
    }

}
