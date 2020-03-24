package com.example.segurados.view.ui.ranking;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.segurados.R;
import com.example.segurados.adapter.RankingAdapter;
import com.example.segurados.model.RankingViewModel;
import com.example.segurados.model.UsuarioViewModel;
import com.example.segurados.service.UsuarioEstatisticaService;
import com.example.segurados.util.Util;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankingFragment extends Fragment {
    private RankingAdapter rankingAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar pB;
    private UsuarioEstatisticaService usuarioEstatisticaService;
    List<RankingViewModel> ranking;
    private Realm realm;
    public RankingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_ranking, container, false);
        mRecyclerView = v.findViewById(R.id.rcvRanking);
        pB = v.findViewById(R.id.progress_bar_r);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        usuarioEstatisticaService = UsuarioEstatisticaService.retrofit.create(UsuarioEstatisticaService.class);

        realm = Realm.getDefaultInstance();
        RealmResults<UsuarioViewModel> user = realm.where(UsuarioViewModel.class).findAll();
        final Call<List<RankingViewModel>> call =  usuarioEstatisticaService.getRanking("bearer " +  user.first().getToken());
        if(Util.checkInternet(getActivity()))
            loadRanking(call);
        else{
            ranking = realm.where(RankingViewModel.class).findAll();
            setRanking(ranking);
        }
        return v;
    }

    private void loadRanking(Call<List<RankingViewModel>> call){
        call.enqueue(new Callback<List<RankingViewModel>>() {
            @Override
            public void onResponse(Call<List<RankingViewModel>> call, Response<List<RankingViewModel>> response) {
                int code = response.code();

                if(code == 200){
                    ranking = response.body();
                    System.out.println(response.body().get(0).getPerfil());
                    realm.beginTransaction();
                    for(RankingViewModel r :ranking) {
                        realm.copyToRealmOrUpdate(r);
                    }
                    realm.commitTransaction();
                   setRanking(ranking);
                }else{

                    Toast.makeText(getContext(),"Falhou",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<RankingViewModel>> call, Throwable t) {

            }
        });
    }

    public void setRanking(List<RankingViewModel> ranking){
        rankingAdapter = new RankingAdapter(ranking, getActivity());
        mRecyclerView.setAdapter(rankingAdapter);
        pB.setVisibility(View.GONE);
        if(ranking.size() == 0)
            Toast.makeText(getActivity(), "Sem jogador para compor o ranking!!", Toast.LENGTH_LONG).show();
    }

}
