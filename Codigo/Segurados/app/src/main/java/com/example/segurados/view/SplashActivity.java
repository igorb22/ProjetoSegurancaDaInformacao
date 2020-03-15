package com.example.segurados.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.segurados.R;
import com.example.segurados.model.Pergunta;
import com.example.segurados.model.Usuario;
import com.example.segurados.model.UsuarioViewModel;
import com.example.segurados.service.PerguntaService;
import com.example.segurados.service.UsuarioService;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    private Realm realm;
    private PerguntaService perguntaService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        perguntaService = PerguntaService.retrofit.create(PerguntaService.class);
        final Call<List<Pergunta>> call = perguntaService.getPerguntas();
        loadQuests(call);

       /* new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        },6000); */
    }

    private void loadQuests(Call<List<Pergunta>> call) {
        call.enqueue(new Callback<List<Pergunta>>() {
            @Override
            public void onResponse(Call<List<Pergunta>> call, Response<List<Pergunta>> response) {
                int code = response.code();

                if (code == 200) {
                    realm = Realm.getDefaultInstance();

                    List<Pergunta> perguntas = response.body();
                    realm.beginTransaction();
                    for(Pergunta p : perguntas) {
                        RealmQuery<Pergunta> obj = realm.where(Pergunta.class).equalTo("idPergunta", p.getIdPergunta());
                        if (obj == null) {
                            Pergunta pergunta = realm.createObject(Pergunta.class, p.getIdPergunta());
                            pergunta.setQuestao(p.getQuestao());
                            pergunta.setAlternativa1(p.getAlternativa1());
                            pergunta.setAlternativa2(p.getAlternativa2());
                            pergunta.setAlternativa3(p.getAlternativa3());
                            pergunta.setAlternativa4(p.getAlternativa4());
                            pergunta.setDificuldade(p.getDificuldade());
                            pergunta.setOpcaoCorreta(p.getOpcaoCorreta());
                            pergunta.setTempo(p.getTempo());
                            pergunta.setTematicaIdTematica(p.getTematicaIdTematica());
                        }
                    }
                    realm.commitTransaction();
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } else {

                    Toast.makeText(SplashActivity.this, "Falhou",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Pergunta>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        new VerificaUsuario().start();

    }


    public class VerificaUsuario extends Thread {
        private RealmResults<UsuarioViewModel> user;

        @Override
        public void run() {

            Realm realm = Realm.getDefaultInstance();
            user = realm.where(UsuarioViewModel.class).findAll();

            if (user.size() > 0) {

                UsuarioService auth = UsuarioService.retrofit.create(UsuarioService.class);
                final Call<Usuario> call = auth.getUsuario(user.get(0).getIdUsuario(),
                        "bearer " + user.get(0).getToken());

                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                        int code = response.code();

                        if (code == 200) {
                            Toast.makeText(getBaseContext(), "requisicao deu certo",
                                    Toast.LENGTH_LONG).show();

                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();

                        } else if (code == 400 || code == 401 || code == 403) {
                            Toast.makeText(getBaseContext(), "Nao autorizado" + code,
                                    Toast.LENGTH_LONG).show();

                            removeUser();
                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                            finish();
                        }

                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {

                        Toast.makeText(getBaseContext(), "Algo deu errado. Verifique sua conexao!.",
                                Toast.LENGTH_LONG).show();

                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();

                    }
                });

            } else {

                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }

            realm.close();
        }

        public void removeUser() {
            Realm realm = Realm.getDefaultInstance();

            realm.beginTransaction();
            RealmResults<UsuarioViewModel> user = realm.where(UsuarioViewModel.class).findAll();

            user.deleteAllFromRealm();

            realm.commitTransaction();


            realm.close();
        }
    }
    
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }

}
