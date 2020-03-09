package com.example.segurados.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.segurados.Interface.Comunicador;
import com.example.segurados.R;
import com.example.segurados.view.ui.CadastrarseFragment;


public class LoginActivity extends AppCompatActivity implements Comunicador {
    private Button btnCadastrar;
    private Button btnEsqueciSenha;
    private Button btnEntrar;
    private LinearLayout containerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        containerLogin = findViewById(R.id.containerLogin);
        containerLogin = findViewById(R.id.containerLogin);
        btnCadastrar = findViewById(R.id.btnCadastro);


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                containerLogin.setVisibility(View.INVISIBLE);

                CadastrarseFragment cadastrase = new CadastrarseFragment(LoginActivity.this);
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.containerFragment, cadastrase);
                ft.commit();
            }
        });




    }

    @Override
    public void layoutIsClosed(boolean status) {
        containerLogin.setVisibility(View.VISIBLE);
    }
}
