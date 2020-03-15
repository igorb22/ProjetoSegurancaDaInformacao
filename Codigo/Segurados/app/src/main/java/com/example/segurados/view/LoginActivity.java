package com.example.segurados.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.segurados.Interface.Comunicador;
import com.example.segurados.R;
import com.example.segurados.model.Usuario;
import com.example.segurados.model.UsuarioViewModel;
import com.example.segurados.service.AuthenticateService;
import com.example.segurados.view.ui.CadastrarUsuario.CadastrarseFragment;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements Comunicador {
    private Button btnCadastrar;
    private Button btnEsqueciSenha;
    private Button btnEntrar;
    private LinearLayout containerLogin;
    private AutoCompleteTextView edtEmail;
    private EditText edtSenha;
    private TextView msgErro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        containerLogin = findViewById(R.id.containerLogin);
        containerLogin = findViewById(R.id.containerLogin);
        btnCadastrar = findViewById(R.id.btnCadastro);
        btnEntrar = findViewById(R.id.btnEntrar);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        msgErro = findViewById(R.id.msgErro);

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


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario user = new Usuario(0,"Igor Bruno",edtEmail.getText().toString(),"olamunod.pbg",edtSenha.getText().toString());

                AuthenticateService auth = AuthenticateService.retrofit.create(AuthenticateService.class);
                final Call<UsuarioViewModel> call = auth.authenticate(user);


                final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
                dialog.setMessage("Fazendo login...");
                dialog.setCancelable(false);
                dialog.show();

                call.enqueue(new Callback<UsuarioViewModel>() {
                    @Override
                    public void onResponse(Call<UsuarioViewModel> call, Response<UsuarioViewModel> response) {

                        if(dialog.isShowing())
                            dialog.dismiss();

                        int code = response.code();

                        if(code == 200){

                            UsuarioViewModel usuarioViewModel = response.body();
                            Toast.makeText(getBaseContext(),"TOKEN: "+usuarioViewModel.getToken(),
                                    Toast.LENGTH_LONG).show();

                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            realm.copyToRealm(usuarioViewModel);
                            realm.commitTransaction();
                            realm.close();

                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            finish();

                        }else if(code == 400) {

                            msgErro.setVisibility(View.VISIBLE);
                        }

                        Toast.makeText(getBaseContext(), "Falhou: " + code, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<UsuarioViewModel> call, Throwable t) {

                        if(dialog.isShowing())
                            dialog.dismiss();


                        msgErro.setText("Algo de inesperado aconteceu. Verifique sua conexao e tente novamente. ");
                        msgErro.setVisibility(View.VISIBLE);

                        Toast.makeText(getBaseContext(),t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public void layoutIsClosed(boolean status) {
        containerLogin.setVisibility(View.VISIBLE);
    }
}
