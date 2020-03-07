package com.example.segurados.view.ui.perfil;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.segurados.R;
import com.example.segurados.model.Usuario;
import com.example.segurados.service.UsuarioService;

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

                /*UsuarioService seguradosUser = UsuarioService.retrofit.create(UsuarioService.class);
                Call<Usuario> users = seguradosUser.getUsuario(1);

                users.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        int code = response.code();

                        if(!response.isSuccessful()){
                            return;
                        }
                    }
                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                    }
                });*/
            }
        });

        return v;
    }

}
