package com.example.agnciadeturismo.presenter.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.model.CartaoDto;
import com.example.agnciadeturismo.model.ClienteDto;

import java.util.ArrayList;

public class CartaoAdapter extends RecyclerView.Adapter<CartaoAdapter.viewHolder> {

    OnClickItemCartao listener;
    ArrayList<CartaoDto> listCartao;

    public CartaoAdapter(OnClickItemCartao listener, ArrayList<CartaoDto> listCartao) {
        this.listener = listener;
        this.listCartao = listCartao;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cartao, parent, false);
        return new viewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        CartaoDto cartaoDto = listCartao.get(position);
        String nome = cartaoDto.getNomeCartao();
        String numero = cartaoDto.getNumeroCartao();
        Log.d("TAG",nome );
        holder.textViewNome.setText(nome);
        holder.textViewNumero.setText("Nº "+numero);
    }

    @Override
    public int getItemCount() {
        return listCartao.size();
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
                    listener.onClickComprar(listCartao.get(getAdapterPosition()).getCd());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onClickRemover(listCartao.get(getAdapterPosition()).getCd());
                    return true;
                }
            });
        }
    }
}
