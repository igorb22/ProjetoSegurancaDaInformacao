package com.example.segurados.view.ui.jogar;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.adefruandta.spinningwheel.SpinningWheelView;
import com.example.segurados.R;
import com.example.segurados.model.Tematica;
import com.example.segurados.service.TematicaService;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class JogarFragment extends Fragment implements SpinningWheelView.OnRotationListener<String>  {

    private SpinningWheelView wheelView;
    private Button btnGirar;
    private ProgressBar pbCarregando;
    private TextView txtCarregando;
    private List<Tematica> tematicaList;
    private TematicaService tematicaService;
    public JogarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_jogar, container, false);

        wheelView = v.findViewById(R.id.wheel_j);
        btnGirar = v.findViewById(R.id.btn_girar_j);
        pbCarregando = v.findViewById(R.id.progress_bar_j);
        txtCarregando = v.findViewById(R.id.txt_carregando_j);

        tematicaService = TematicaService.retrofit.create(TematicaService.class);
        final Call<List<Tematica>> call = tematicaService.getTematicas();
        loadDataTematic(call);

        return v;
    }

    @Override
    public void onRotation() {
        //Log.d("XXXX", "On Rotation");
    }

    @Override
    public void onStopRotation(String item) {
        Toast.makeText(getActivity(), item, Toast.LENGTH_LONG).show();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PerguntaFragment perguntaFragment = new PerguntaFragment();
        ft.replace(R.id.containerFragment, perguntaFragment);
        ft.commit();
    }

    private void loadDataTematic(Call<List<Tematica>> call){
        call.enqueue(new Callback<List<Tematica>>() {
            @Override
            public void onResponse(Call<List<Tematica>> call, Response<List<Tematica>> response) {
                int code = response.code();

                if(code == 200){
                    tematicaList = response.body();
                    setWheelSpin();
                }else{

                    Toast.makeText(getContext(),"Falhou",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Tematica>> call, Throwable t) {

            }
        });
    }

    public void setWheelSpin(){
        List<String> tituloTemas = new ArrayList<>();
        for(Tematica t : tematicaList)
            tituloTemas.add(t.getTitulo());

        wheelView.setItems(tituloTemas);
        wheelView.setOnRotationListener(this);
        wheelView.setColors(ColorTemplate.PASTEL_COLORS);
        wheelView.setWheelTextColor(Color.WHITE);
        wheelView.setWheelTextSize(15f);
        pbCarregando.setVisibility(View.GONE);
        txtCarregando.setVisibility(View.GONE);
        wheelView.setVisibility(View.VISIBLE);
        btnGirar.setVisibility(View.VISIBLE);

        btnGirar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // max angle 50
                // duration 10 second
                // every 50 ms rander rotation
                wheelView.rotate(50, 3000, 50);
            }
        });
    }
}
