package com.example.agnciadeturismo.presenter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agnciadeturismo.R;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.viewHolder> {

    OnClickItemCarrinho listener;

    public CarrinhoAdapter(OnClickItemCarrinho listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_carrinho, parent, false);
        return new viewHolder(view);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.textViewNome.setText("Pacote para o Rio de Janeiro");
        holder.textViewValor.setText("R$700");
        holder.textViewOrigemDestino.setText("Origem e Destino");
    }

    class viewHolder extends RecyclerView.ViewHolder {

        Button buttonComprar;
        TextView textViewRemover, textViewOrigemDestino, textViewValor, textViewNome;
        ImageView imageView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            buttonComprar = itemView.findViewById(R.id.btn_comprarCarrinho);
            textViewRemover = itemView.findViewById(R.id.txt_removerCarrinho);
            imageView = itemView.findViewById(R.id.img_carrinho);
            textViewNome = itemView.findViewById(R.id.txt_nomeCarrinho);
            textViewValor = itemView.findViewById(R.id.txt_valorCarrinho);
            textViewOrigemDestino = itemView.findViewById(R.id.txt_destinoCarrinho);


            buttonComprar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickComprar(getAdapterPosition());
                }
            });
            textViewRemover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickRemover(getAdapterPosition());
                }
            });
        }
    }
}
