package com.example.segurados.view.ui.jogar;


import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.segurados.R;
import com.example.segurados.adapter.OpcaoAdapter;
import com.example.segurados.model.Opcao;
import com.example.segurados.model.Pergunta;
import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerguntaFragment extends Fragment {
    private RecyclerView mRecyclerView;
  //  private AdapterOpcao adapterOpcao;
    private TextView txtPergunta;
    private CardView cardView;
    private TextView txtLog;
    private TextView txtPontos;
    private ImageView imgResposta;
    private Button btnContinuar;
    private Button btnFinalizar;
    // detalhe de interface
    private LinearLayout linearLayout;
    private int tempo;
    private int progressStatus;
    private DonutProgress donutProgress;
    private ProgressBar progressBar;
    private int verificaProgress;
    private ArrayList<Opcao> opcoes;
    private List<Pergunta> perguntaList;

    public PerguntaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_pergunta, container, false);
        Realm realm = Realm.getDefaultInstance();
        perguntaList = realm.where(Pergunta.class).findAll();

      txtPergunta = v.findViewById(R.id.txt_pergunta_pg);
      // ----------------------------------------------------------------------
      if (perguntaList.size() > 0) {
        // referencia dos itens do carView
        cardView = v.findViewById(R.id.mycard_pg);
        txtPontos = v.findViewById(R.id.txt_pontuacao_obtida_pg);
        txtLog = v.findViewById(R.id.txt_log_resposta_pg);
        imgResposta = v.findViewById(R.id.img_resposta_pg);
        btnContinuar = v.findViewById(R.id.btn_continuar_pg);
        btnFinalizar = v.findViewById(R.id.btn_finalizar_pg);
        // refenrencia dos itens do progressbar
        donutProgress = v.findViewById(R.id.donut_progress_pg);
        // recycler
        mRecyclerView = v.findViewById(R.id.rcv_opcao_pg);
        // pergunta
        txtPergunta = v.findViewById(R.id.txt_pergunta_pg);
        progressBar = v.findViewById(R.id.progress_pg);
        linearLayout = v.findViewById(R.id.linear);

        // ------------------------------------------------------------------
        opcoes = new ArrayList<>();
        txtPergunta.setText(perguntaList.get(0).getQuestao());

        Opcao op = new Opcao(perguntaList.get(0).getAlternativa1());
        opcoes.add(op);
        op = new Opcao(perguntaList.get(0).getAlternativa2());
        opcoes.add(op);
        op = new Opcao(perguntaList.get(0).getAlternativa3());
        opcoes.add(op);
        op = new Opcao(perguntaList.get(0).getAlternativa4());
        opcoes.add(op);
        // ---------------------------------------------------------------------
        //circle progress

        donutProgress.setFinishedStrokeColor(ContextCompat.getColor(Objects.requireNonNull(getActivity()), R.color.colorPrimaryDark));
        donutProgress.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getActivity()), R.color.colorAccent));
        donutProgress.setKeepScreenOn(true);
        donutProgress.setSuffixText("s");
        //---------------------------------------------------------------------
        //Componentes do Recicler
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);

        OpcaoAdapter opcaoAdapter= new OpcaoAdapter(opcoes, getContext());
        mRecyclerView.setAdapter(opcaoAdapter);

        // -----------------------------------------------------------------------
        progressStatus =  perguntaList.get(0).getTempo();
        int tmax  = perguntaList.get(0).getTempo();
        donutProgress.setMax(tmax);
        // ProgressBar que conta o tempo de resposta do usuário
        Handler handler = new Handler();
        new Thread(new Runnable() {
          public void run() {
            while (progressStatus >= 0) {
              handler.post(new Runnable() {
                public void run() {
                  donutProgress.setProgress(progressStatus);
                  verificaProgress = progressStatus;
                  if (progressStatus == 0) {
                    menuOpcao(2);
                  }
                }
              });
              try {
                // Sleep for 1000 milliseconds.
                Thread.sleep(1000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              progressStatus -= 1;
            }
          }
        }).start();
        // -----------------------------------------------------------------------------
        opcaoAdapter.setOnItemClickListener(position -> {
          tempo = progressStatus;
          progressStatus = 0;
          if (tempo > 0) {
            // verificando se a opcao escolhida foi a correta
            if (position == perguntaList.get(0).getOpcaoCorreta()-1) {
              menuOpcao(0);
             /* gravaResposta();  aqui o momento em que eu gravo a resposta,
               * quando o usuário clica, mas só grava se a resposta for correta */
            }else
              menuOpcao(1); /* resposta errada */
          }else
            menuOpcao(2); /* tempo esgotado */
        });
        // -------------------------------------------------------------------------------
        btnContinuar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            linearLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            // ------------------------------------------------------------------
            // removendo o primeiro elemento do array, assim sempre podemos selecionar a pergunta de indice 0
         //   perguntas.remove(0);
            // reiniciando fragment
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.containerFragment, new PerguntaFragment()).commit();

          }
        });



        btnFinalizar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            // aqui a requisicão
            linearLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            // ------------------------------------------------------------------
          //  comunicador.fimJogo(true);
          }
        });
        // -------------------------------------------------------------------------------
      }else {
        //donutProgress.setVisibility(View.GONE);
        txtPergunta.setText("As perguntas acabaram, tente novamente mais tarde!");
      }

      return v;
    }

  public void menuOpcao(int resposta){
    if (cardView.getVisibility() == View.GONE){
      if (resposta == 0){
        txtLog.setText("Resposta Correta!");
        txtPontos.setText("Pontuacao: "+tempo*10);
        imgResposta.setImageResource(R.drawable.certo);
      }else if (resposta == 1){
        txtLog.setText("Resposta Errada!");
        imgResposta.setImageResource(R.drawable.errado);
        txtPontos.setText("Pontuação: "+0);
      }else if (resposta == 2){
        txtLog.setText("Seu tempo Acabou!");
        imgResposta.setImageResource(R.drawable.errado);
        txtPontos.setText("Pontuação: "+0);
      }
      cardView.setVisibility(View.VISIBLE);
    }
  }


}
