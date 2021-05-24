package com.example.agnciadeturismo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

public class RecentementeAdapter extends RecyclerView.Adapter<RecentementeAdapter.viewHolder> {

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recentemente, parent, false);
        return new viewHolder(view);
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
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNome = itemView.findViewById(R.id.txt_nomeRecentemente);
            textViewValor = itemView.findViewById(R.id.txt_valorRecentemente);
        }
    }
}
