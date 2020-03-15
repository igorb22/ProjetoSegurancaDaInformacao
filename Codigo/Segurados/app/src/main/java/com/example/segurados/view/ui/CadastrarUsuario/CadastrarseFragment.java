package com.example.segurados.view.ui.CadastrarUsuario;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.segurados.Interface.Comunicador;
import com.example.segurados.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastrarseFragment extends Fragment {
    private Comunicador comunicador;

    public CadastrarseFragment(Context ctx) {
        comunicador = (Comunicador) ctx;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.layout_fechar, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_back:

                comunicador.layoutIsClosed(true);

                final FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.containerFragment, new Fragment());
                ft.commit();

                return true;
        }

        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_cadastrarse, container, false);

        setHasOptionsMenu(true);



        return v;
    }

}
