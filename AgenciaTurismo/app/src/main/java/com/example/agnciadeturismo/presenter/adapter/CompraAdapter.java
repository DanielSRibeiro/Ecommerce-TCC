package com.example.agnciadeturismo.presenter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agnciadeturismo.R;

public class CompraAdapter extends RecyclerView.Adapter<CompraAdapter.viewHolder> {

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_compra, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.textViewNome.setText("Pacote para o Rio de Janeiro");
        holder.textViewValor.setText("R$700");
        holder.textViewOrigemDestino.setText("Origem e Destino");
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class viewHolder extends RecyclerView.ViewHolder {

        TextView textViewOrigemDestino, textViewValor, textViewNome;
        ImageView imageView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_compra);
            textViewNome = itemView.findViewById(R.id.txt_nomeCompra);
            textViewValor = itemView.findViewById(R.id.txt_valorCompra);
            textViewOrigemDestino = itemView.findViewById(R.id.txt_destinoCompra);
        }
    }
}
