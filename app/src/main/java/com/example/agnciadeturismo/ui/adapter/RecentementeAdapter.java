package com.example.agnciadeturismo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agnciadeturismo.R;

public class RecentementeAdapter extends RecyclerView.Adapter<RecentementeAdapter.viewHolder> {

    OnClickItemPacote listener;

    public RecentementeAdapter(OnClickItemPacote listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recentemente, parent, false);
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

            textViewNome = itemView.findViewById(R.id.txt_nomeRecentemente);
            textViewValor = itemView.findViewById(R.id.txt_valorRecentemente);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(getAdapterPosition());
                }
            });
        }
    }
}
