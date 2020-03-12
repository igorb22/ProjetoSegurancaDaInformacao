package com.example.segurados.view.ui.perfil;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.segurados.R;
import com.example.segurados.model.Usuario;
import com.example.segurados.model.UsuarioHasPergunta;
import com.example.segurados.model.UsuarioViewModel;
import com.example.segurados.service.UsuarioEstatisticaService;
import com.example.segurados.service.UsuarioHasPerguntaService;
import com.example.segurados.service.UsuarioService;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {
    private UsuarioService usuarioService;
    private UsuarioEstatisticaService usuarioEstatisticaService;
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
  ///  private ArrayList PieEntryLabels;

    public PerfilFragment() {
        // Required empty public constructor
    }

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

        //----------------- Grafico Pizza ---------------------//

        //------------------ get data User --------------------//

        usuarioService = UsuarioService.retrofit.create(UsuarioService.class);
        usuarioEstatisticaService = UsuarioEstatisticaService.retrofit.create(UsuarioEstatisticaService.class);
        usuarioHasPergunta = UsuarioHasPerguntaService.retrofit.create(UsuarioHasPerguntaService.class);

        final Call<Usuario> call = usuarioService.getUsuario(2);
   //     final Call<List<UsuarioViewModel>> callEst = usuarioEstatisticaService.getEstatistica(2);

        loadDataProfile(call);
      //  loadEstsProfile(callEst);




        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo EDITAR PERFIL
            }
        });
        return v;
    }
       @SuppressLint("SetTextI18n")
       private void setGraphic(List<UsuarioViewModel> estats) {
        pieEntries = new ArrayList<>();
        int pontos = 0;
        for(UsuarioViewModel us : estats){
            pieEntries.add(new PieEntry(us.getPontos(), us.getTematica().getTitulo()));
            pontos += us.getPontos();
        }
           pontosUsuario.setText(pontos + " " + getString(R.string.pontoss));
           pieDataSet = new PieDataSet(pieEntries, "");
           pieData = new PieData(pieDataSet);
           //   dataSet.setValueFormatter(get);
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


    private void loadDataProfile(Call<Usuario> call){
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                int code = response.code();

                if(code == 200){
                    Usuario usuario = response.body();
                    txtNomeUsuario.setText(usuario.getNome());
                    Glide.with(Objects.requireNonNull(getActivity()))
                            .load(usuario.getPerfil())
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
                    final Call<List<UsuarioViewModel>> callEst = usuarioEstatisticaService.getEstatistica(2);
                    loadEstsProfile(callEst);

                }else{

                    Toast.makeText(getContext(),"Falhou",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });
    }

    private void loadEstsProfile(Call<List<UsuarioViewModel>> call){
        call.enqueue(new Callback<List<UsuarioViewModel>>() {
            @Override
            public void onResponse(Call<List<UsuarioViewModel>> call, Response<List<UsuarioViewModel>> response) {
                int code = response.code();

                if(code == 200){
                    List<UsuarioViewModel> estatsUsuario = response.body();
                    setGraphic(estatsUsuario);
                    System.out.println("opa");
                    final Call<List<UsuarioHasPergunta>> callQ = usuarioHasPergunta.getRespostasUsuario(2);
                    loadQtdPrguntas(callQ);
                }else{

                    Toast.makeText(getContext(),"Falhou",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<UsuarioViewModel>> call, Throwable t) {

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
                    List<UsuarioHasPergunta> estatsUsuario= response.body();
                    System.out.println("s " + estatsUsuario.size());
                    qtdPerguntas.setText(estatsUsuario.size() + " " + getString(R.string.qtd_perguntas_t));

                }else{

                    Toast.makeText(getContext(),"Falhou",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<UsuarioHasPergunta>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
