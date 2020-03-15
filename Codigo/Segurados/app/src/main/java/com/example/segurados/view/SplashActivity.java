package com.example.segurados.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.segurados.R;
import com.example.segurados.model.Pergunta;
import com.example.segurados.model.Usuario;
import com.example.segurados.service.PerguntaService;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
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
    }
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }

}
