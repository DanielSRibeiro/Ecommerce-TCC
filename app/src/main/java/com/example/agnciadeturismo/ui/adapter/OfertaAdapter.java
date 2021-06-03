package com.example.agnciadeturismo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agnciadeturismo.R;

public class OfertaAdapter extends RecyclerView.Adapter<OfertaAdapter.viewHolder> {

    OnClickItemPacote listener;

    public OfertaAdapter(OnClickItemPacote listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_oferta, parent, false);
        return new viewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.textViewNome.setText("Pacote para o Rio de Janeiro");
        holder.textViewValor.setText("R$300,00");
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView textViewNome, textViewValor;
        public viewHolder(@NonNull View itemView, OnClickItemPacote listener) {
            super(itemView);

            textViewNome = itemView.findViewById(R.id.txt_nomeOferta);
            textViewValor = itemView.findViewById(R.id.txt_valorOferta);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(getAdapterPosition());
                }
            });
        }
    }
}
