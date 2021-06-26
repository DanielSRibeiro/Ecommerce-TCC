package com.example.agnciadeturismo.presenter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.model.ReservaDto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CompraAdapter extends RecyclerView.Adapter<CompraAdapter.viewHolder> {

    OnClickItemCompra listener;
    ArrayList<ReservaDto> listReserva;

    public CompraAdapter(OnClickItemCompra listener, ArrayList<ReservaDto> listReserva) {
        this.listener = listener;
        this.listReserva = listReserva;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_compra, parent, false);
        return new viewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ReservaDto reserva = listReserva.get(position);
        holder.textViewValor.setText("R$"+reserva.getValorTotal());
        holder.textViewCartao.setText(reserva.getNomeCartao());
        holder.textViewCliente.setText(reserva.getNomeCliente());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        String data = dateFormat.format(reserva.getData());
        holder.textViewData.setText(reserva.getData());
    }

    @Override
    public int getItemCount() {
        return listReserva.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{

        TextView textViewValor, textViewCartao, textViewCliente, textViewData;

        public viewHolder(@NonNull View itemView, OnClickItemCompra listener) {
            super(itemView);

            textViewValor = itemView.findViewById(R.id.txt_valorItem);
            textViewCartao = itemView.findViewById(R.id.txt_nomeCartaoItem);
            textViewCliente = itemView.findViewById(R.id.txt_nomeClienteItem);
            textViewData = itemView.findViewById(R.id.txt_dataItem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(getAdapterPosition());
                }
            });
        }
    }
}
