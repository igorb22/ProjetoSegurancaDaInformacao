package com.example.segurados.view.ui.jogar;


import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.segurados.R;
import com.github.lzyzsd.circleprogress.DonutProgress;

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

    public PerguntaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pergunta, container, false);
    }

}
