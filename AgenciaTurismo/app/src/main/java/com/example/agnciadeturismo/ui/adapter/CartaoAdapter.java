package com.example.agnciadeturismo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agnciadeturismo.R;

public class CartaoAdapter extends RecyclerView.Adapter<CartaoAdapter.viewHolder> {

    OnClickItemCartao listener;

    public CartaoAdapter(OnClickItemCartao listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cartao, parent, false);
        return new viewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.textViewNome.setText("Nome do Cartão");
        holder.textViewNumero.setText("Nº 0000 0000 0000 0000");
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class viewHolder extends RecyclerView.ViewHolder {

        TextView textViewNome, textViewNumero;

        public viewHolder(@NonNull View itemView, OnClickItemCartao listener) {
            super(itemView);

            textViewNome = itemView.findViewById(R.id.txt_nomeCartao);
            textViewNumero = itemView.findViewById(R.id.txt_numeroCartao);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(getAdapterPosition());
                }
            });
        }
    }
}
