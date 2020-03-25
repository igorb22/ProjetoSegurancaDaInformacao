package com.example.segurados.view.ui.perfil;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.segurados.R;
import com.example.segurados.model.PontosUsuarioViewModel;
import com.example.segurados.model.UsuarioHasPergunta;
import com.example.segurados.model.UsuarioViewModel;
import com.example.segurados.service.UsuarioHasPerguntaService;
import com.example.segurados.util.Util;
import com.example.segurados.view.LoginActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private UsuarioHasPerguntaService usuarioHasPergunta;
    private TextView txtNomeUsuario;
    private TextView pontosUsuario;
    private TextView qtdPerguntas;
    private CircleImageView imgPerfil;
    private Button btnEditar;
    private PieChart pieChart;
    private PieData pieData;
    private PieDataSet pieDataSet;
    private ArrayList pieEntries;
  ///  arquivo pra saber se tem respostas nao salvas
    private File f;
    public PerfilFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_perfil, container, false);
        btnEditar = v.findViewById(R.id.btn_editar_p);
        pieChart = v.findViewById(R.id.pie_chart_p);
        txtNomeUsuario = v.findViewById(R.id.nome_jogador_p);
        pontosUsuario = v.findViewById(R.id.pontos_jogador_p);
        qtdPerguntas = v.findViewById(R.id.qtd_questoes_p);
        imgPerfil = v.findViewById(R.id.img_perfil_p);

        setHasOptionsMenu(true);


        //------------------ get data User --------------------//
        Realm realm = Realm.getDefaultInstance();
        RealmResults<UsuarioViewModel> usuario = realm.where(UsuarioViewModel.class).findAll();

        // salvas respostas caso n tenha salvo
        f = new File(getActivity().getFilesDir()+"/filaRespostas.txt");
        if(f.exists()){
            if(Util.checkInternet(getActivity())){
                String linha;

                try {
                    BufferedReader br = new BufferedReader(new FileReader(f));
                    linha = br.readLine();
                    while (linha != null){
                        String [] dados = linha.split(";");
                        new Util.AddResposta( getActivity(),            //id Usuario     //id Pergunta             //acertou int
                                new UsuarioHasPergunta(Integer.parseInt(dados[1]), Integer.parseInt(dados[0]), Integer.parseInt(dados[2])), usuario.first().getToken()).start();
                    }
                    br.close();
                    f.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //------------------------------------------------------------------------------------------

        System.out.println(usuario.first().toString());
        txtNomeUsuario.setText(usuario.first().getNome());
        qtdPerguntas.setText(usuario.first().getQtdQuestoes() + " " + getString(R.string.qtd_questoes));
        Glide.with(getActivity())
                .load(usuario.first().getPerfil())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imgPerfil);      //classe que pega a foto da url e seta o imageView
        // --------------------------------------------------------
        RealmResults<PontosUsuarioViewModel> pU = realm.where(PontosUsuarioViewModel.class).findAll();

        setGraphic(pU);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo EDITAR PERFIL

            }
        });
        return v;
    }
       @SuppressLint("SetTextI18n")
       private void setGraphic(RealmResults<PontosUsuarioViewModel> estats) {
        pieEntries = new ArrayList<>();
        int pontos = 0;
        for(PontosUsuarioViewModel us : estats){
            pieEntries.add(new PieEntry(us.getPontos(), us.getTematica().getTitulo()));
            pontos += us.getPontos();
        }
           pontosUsuario.setText(pontos + " " + getString(R.string.pontoss));
           pieDataSet = new PieDataSet(pieEntries, "");
           pieData = new PieData(pieDataSet);
           pieChart.setData(pieData);
           pieChart.setDrawCenterText(true);
           pieChart.setDrawHoleEnabled(false);
           pieChart.getDescription().setText("Legenda");
           pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
           pieDataSet.setSliceSpace(1f);
           pieDataSet.setValueTextColor(Color.WHITE);
           pieDataSet.setValueTextSize(10f);
           pieChart.invalidate();
       }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        inflater.inflate(R.menu.menu_sair, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.op_sair) {
            if(Util.checkInternet(getActivity())){
                if(f.exists())
                    Toast.makeText(getActivity(), "Jogo sem internet, alguma(s) respostas não foram salvas.", Toast.LENGTH_LONG).show();
            }
            Util.removeUser();
            startActivity(new Intent(getActivity(), LoginActivity.class));
            Objects.requireNonNull(getActivity()).finish();
            return true;
        }

        return false;
    }
}
