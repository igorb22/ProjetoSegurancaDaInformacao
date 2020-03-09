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


                UsuarioService usuario = UsuarioService.retrofit.create(UsuarioService.class);
                final Call<Usuario> call = usuario.authenticate("igorb22@live.com","98425537");


                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        int code = response.code();

                        if(code == 200){
                            Usuario usuarios = response.body();

                            Toast.makeText(getContext(),"NOME: "+usuarios.getNome()
                                            +"EMAIL: "+usuarios.getPerfil()
                                            +"ID: "+usuarios.getIdUsuario()
                                            +"PERFIL: "+usuarios.getEmail(),
                                    Toast.LENGTH_LONG).show();

                        }else{

                            Toast.makeText(getContext(),"Falhou: "+code,
                                    Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(getContext(),t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }


                });


            }
        });

        return v;
    }

}
