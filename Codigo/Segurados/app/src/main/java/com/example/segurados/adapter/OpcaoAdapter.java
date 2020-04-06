package com.example.segurados.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.segurados.R;
import com.example.segurados.model.Opcao;

import java.io.IOException;
import java.util.List;

public class OpcaoAdapter extends RecyclerView.Adapter<OpcaoAdapter.OpcaoViewHolder> {
    private OpcaoAdapter.OnItemClickListener mListener;
    private List<Opcao> opcoes;
    private Context ctx;

    public OpcaoAdapter(List<Opcao> opcoes,Context context) {
        this.opcoes = opcoes;
        this.ctx = context;
    }

    @NonNull
    @Override
    public OpcaoAdapter.OpcaoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_opcao, viewGroup, false);

        OpcaoAdapter.OpcaoViewHolder holder = new OpcaoAdapter.OpcaoViewHolder(view,mListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OpcaoViewHolder opcaoViewHolder, int i) {
        opcaoViewHolder.txt.setText(opcoes.get(i).getOpcao());
    }


    public class OpcaoViewHolder extends RecyclerView.ViewHolder {
        final TextView txt;
        int position;

        public OpcaoViewHolder(View itemView, OpcaoAdapter.OnItemClickListener listener) {
            super(itemView);
            this.txt = itemView.findViewById(R.id.txtOpcao);


            itemView.setOnClickListener(v -> {
                if (listener != null){
                    position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        try {
                            listener.onItemClicked(position);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

    }


    public void setOnItemClickListener(OpcaoAdapter.OnItemClickListener listener){
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClicked(int position) throws IOException;
    }

    @Override
    public int getItemCount() {
        return opcoes.size();
    }
}
