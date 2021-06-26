package com.example.agnciadeturismo.presenter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.data.api.RetrofitTask;
import com.example.agnciadeturismo.model.ItensReservaDto;
import com.example.agnciadeturismo.presenter.ui.ItensReservaActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItensAdapter extends RecyclerView.Adapter<ItensAdapter.viewHolder> {
    ArrayList<ItensReservaDto> listItens;

    public ItensAdapter(ArrayList<ItensReservaDto> listItens) {
        this.listItens = listItens;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_itenscompras, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ItensReservaDto itens = listItens.get(position);
        holder.textViewNome.setText(itens.getNomePacote());
        holder.textViewValor.setText("R$"+itens.getValorTotal());
        holder.textViewOrigemDestino.setText("Destino: "+itens.getDestino());
        Picasso.get().load("http://"+ RetrofitTask.IP +"/"+itens.getImg()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return listItens.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {

        TextView textViewOrigemDestino, textViewValor, textViewNome;
        ImageView img;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_compra);
            textViewNome = itemView.findViewById(R.id.txt_nomeCompra);
            textViewValor = itemView.findViewById(R.id.txt_valorCompra);
            textViewOrigemDestino = itemView.findViewById(R.id.txt_destinoCompra);
        }
    }
}
