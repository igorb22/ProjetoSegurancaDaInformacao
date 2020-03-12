package com.example.segurados.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.segurados.R;
import com.example.segurados.model.RankingViewModel;

import java.util.List;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingViewHolder> {
    private RankingAdapter.OnItemClickListener mListener;
    private List<RankingViewModel> rankingViewModels;
    private Context context;

    public RankingAdapter( List<RankingViewModel> rankingViewModels, Context context) {
        this.rankingViewModels = rankingViewModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RankingAdapter.RankingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_ranking, viewGroup, false);
        RankingAdapter.RankingViewHolder holder = new RankingAdapter.RankingViewHolder(view,mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RankingViewHolder  viewHolder, int i) {
        //viewHolder.img baixar a imagem e setar
        viewHolder.nome.setText(rankingViewModels.get(i).getNomeUsuario());
        viewHolder.pontuacao.setText(""+rankingViewModels.get(i).getPontos());
        viewHolder.posicao.setText(""+(i+1));
        if(i == 0)
            viewHolder.bestPlayer.setVisibility(View.VISIBLE);

        Glide.with(context)
                .load(rankingViewModels.get(i).getPerfil())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        viewHolder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        viewHolder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(viewHolder.img);      //classe que pega a foto da url e seta o imageView

    }

    public class RankingViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView nome;
        TextView pontuacao;
        TextView posicao;
        ImageView bestPlayer;
        ProgressBar progressBar;
        int position;

        public RankingViewHolder(@NonNull View itemView, RankingAdapter.OnItemClickListener listener) {
            super(itemView);
            this.img = itemView.findViewById(R.id.img_perfil_r);
            this.nome = itemView.findViewById(R.id.txt_usuario_r);
            this.pontuacao = itemView.findViewById(R.id.txt_pontuacao_r);
            this.posicao = itemView.findViewById(R.id.txt_posicao_r);
            this.bestPlayer = itemView.findViewById(R.id.star_r);
            this.progressBar = itemView.findViewById(R.id.progress_bar_r);
            itemView.setOnClickListener(v -> {
                if (listener != null){
                    position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onItemClicked(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return rankingViewModels.size();
    }

    public void setOnItemClickListener(RankingAdapter.OnItemClickListener listener){
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

}

