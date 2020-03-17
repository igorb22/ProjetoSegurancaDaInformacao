package com.example.segurados.view;

import android.annotation.SuppressLint;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.segurados.Interface.Comunicador;
import com.example.segurados.R;
import com.example.segurados.model.Pergunta;
import com.example.segurados.model.PontosUsuarioViewModel;
import com.example.segurados.model.Usuario;
import com.example.segurados.model.UsuarioHasPergunta;
import com.example.segurados.model.UsuarioViewModel;
import com.example.segurados.service.AuthenticateService;
import com.example.segurados.service.UsuarioEstatisticaService;
import com.example.segurados.service.UsuarioHasPerguntaService;
import com.example.segurados.service.UsuarioService;
import com.example.segurados.view.ui.CadastrarUsuario.CadastrarseFragment;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
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
    private ProgressDialog dialog;
    private  Realm realm;
    private UsuarioHasPerguntaService usuarioHasPerguntaService;
    private UsuarioEstatisticaService usuarioEstatisticaService;
    private UsuarioViewModel usuarioViewModel;
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

                dialog = new ProgressDialog(LoginActivity.this);
                dialog.setMessage("Fazendo login...");
                dialog.setCancelable(false);
                dialog.show();

                call.enqueue(new Callback<UsuarioViewModel>() {
                    @Override
                    public void onResponse(Call<UsuarioViewModel> call, Response<UsuarioViewModel> response) {

                        int code = response.code();
                        if (code == 200) {

                            usuarioViewModel = response.body();
                            Toast.makeText(getBaseContext(), "TOKEN: " + usuarioViewModel.getToken(),
                                    Toast.LENGTH_LONG).show();
                            System.out.println(usuarioViewModel.getToken());

                            realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            realm.copyToRealmOrUpdate(usuarioViewModel);
                            realm.commitTransaction();
                            //     realm.close();

                            usuarioEstatisticaService = UsuarioEstatisticaService.retrofit.create(UsuarioEstatisticaService.class);
                            final Call<List<PontosUsuarioViewModel>> callEst = usuarioEstatisticaService.getEstatistica(usuarioViewModel.getIdUsuario(),
                                    "bearer " +  usuarioViewModel.getToken());
                            loadEstsProfile(callEst);

                        } else { // if(code == 400) {

                            msgErro.setVisibility(View.VISIBLE);
                            //     }
                            Toast.makeText(getBaseContext(), "Falhou: " + code, Toast.LENGTH_LONG).show();

                            if (dialog.isShowing())
                                dialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<UsuarioViewModel> call, Throwable t) {
                        t.printStackTrace();
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

    private void loadEstsProfile(Call<List<PontosUsuarioViewModel>> call){
        call.enqueue(new Callback<List<PontosUsuarioViewModel>>() {
            @Override
            public void onResponse(Call<List<PontosUsuarioViewModel>> call, Response<List<PontosUsuarioViewModel>> response) {
                int code = response.code();

                if(code == 200){
                    List<PontosUsuarioViewModel> estatsUsuario = response.body();
                    realm.beginTransaction();
                    for(PontosUsuarioViewModel p : estatsUsuario){
                        realm.copyToRealmOrUpdate(p);
                    }
                    realm.commitTransaction();
                   // realm.close();
                    usuarioHasPerguntaService = UsuarioHasPerguntaService.retrofit.create(UsuarioHasPerguntaService.class);
                    final Call<List<UsuarioHasPergunta>> callQ = usuarioHasPerguntaService.getRespostasUsuario(usuarioViewModel.getIdUsuario(),
                            "bearer " +  usuarioViewModel.getToken());
                    loadQtdPrguntas(callQ);

                }else{

                    Toast.makeText(LoginActivity.this,"Falhou " + code,
                            Toast.LENGTH_LONG).show();
                    System.out.println(response.errorBody());

                    if(dialog.isShowing())
                        dialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<PontosUsuarioViewModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadQtdPrguntas(Call<List<UsuarioHasPergunta>> call){
        call.enqueue(new Callback<List<UsuarioHasPergunta>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<UsuarioHasPergunta>> call, Response<List<UsuarioHasPergunta>> response) {
                int code = response.code();

                if(code == 200){
                    List<UsuarioHasPergunta> estatsUsuario = response.body();
                    realm.beginTransaction();

                    // add ou atualizar pontuacao
                    RealmResults<UsuarioViewModel> obj = realm.where(UsuarioViewModel.class).findAll();
                        obj.get(0).setQtdQuestoes(estatsUsuario.size());
                        System.out.println("po " + estatsUsuario.size());
                        realm.copyToRealmOrUpdate(obj);

                    for(UsuarioHasPergunta uH : estatsUsuario){
                       UsuarioHasPergunta up = realm.createObject(UsuarioHasPergunta.class, uH.getIdPergunta());
                       up.setIdUsuario(uH.getIdUsuario());
                       up.setAcertou(uH.isAcertou());
                       realm.copyToRealmOrUpdate(up);
                    }
                    realm.commitTransaction();
                    realm.close();

                    if(dialog.isShowing())
                        dialog.dismiss();

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();

                }else{

                    Toast.makeText(LoginActivity.this,"Falhou " + code,
                            Toast.LENGTH_LONG).show();
                    System.out.println(response.errorBody());
                    if(dialog.isShowing())
                        dialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<UsuarioHasPergunta>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
