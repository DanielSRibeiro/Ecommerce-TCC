package com.example.agnciadeturismo.presenter.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.data.api.RetrofitTask;
import com.example.agnciadeturismo.model.CarrinhoDto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.viewHolder> {

    OnClickItemCarrinho listener;
    ArrayList<CarrinhoDto> listCarrinho;

    public CarrinhoAdapter(OnClickItemCarrinho listener, ArrayList<CarrinhoDto> listCarrinho) {
        this.listener = listener;
        this.listCarrinho = listCarrinho;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_carrinho, parent, false);
        return new viewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listCarrinho.size();
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        CarrinhoDto carrinho = listCarrinho.get(position);
        holder.textViewNome.setText(carrinho.getNomePacote());
        holder.textViewValor.setText("R$"+carrinho.getValor());
        holder.textViewDestino.setText(carrinho.getDestino());
        Picasso.get().load("http://"+ RetrofitTask.IP+"/"+carrinho.getImg()).into(holder.img);
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView textViewRemover, textViewDestino, textViewValor, textViewNome;
        ImageView img;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRemover = itemView.findViewById(R.id.txt_removerCarrinho);
            img = itemView.findViewById(R.id.img_carrinho);
            textViewNome = itemView.findViewById(R.id.txt_nomeCarrinho);
            textViewValor = itemView.findViewById(R.id.txt_valorCarrinho);
            textViewDestino = itemView.findViewById(R.id.txt_destinoCarrinho);
            textViewRemover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickRemover(getAdapterPosition());
                }
            });
        }
    }
}
