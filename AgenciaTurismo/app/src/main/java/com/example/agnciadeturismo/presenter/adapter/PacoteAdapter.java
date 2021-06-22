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
import com.example.agnciadeturismo.model.PacoteDto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PacoteAdapter extends RecyclerView.Adapter<PacoteAdapter.viewHolder> {

    OnClickItemPacote listener;
    ArrayList<PacoteDto> listPacote;

    public PacoteAdapter(OnClickItemPacote listener, ArrayList<PacoteDto> listPacote) {
        this.listener = listener;
        this.listPacote = listPacote;
    }

    public PacoteAdapter(OnClickItemPacote listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pacote, parent, false);
        return new viewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PacoteDto pacote = listPacote.get(position);
        holder.textViewNome.setText(pacote.getNomePacote());
        holder.textViewDescricao.setText(pacote.getDescricaoPacote());
        holder.textViewValor.setText("R$"+pacote.getVlPacote());
        Picasso.get().load("http://"+ RetrofitTask.IP +"/"+pacote.getImg()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return listPacote.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView textViewNome, textViewDiaria,textViewValor, textViewDescricao;
        ImageView img;
        public viewHolder(@NonNull View itemView, OnClickItemPacote listener) {
            super(itemView);

            textViewNome = itemView.findViewById(R.id.txt_nomePacote);
            textViewDescricao = itemView.findViewById(R.id.txt_descricaoPacote);
            textViewDiaria = itemView.findViewById(R.id.txt_totalDiaria);
            textViewValor = itemView.findViewById(R.id.txt_valorPacote);
            img = itemView.findViewById(R.id.img_pacote);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(getAdapterPosition());
                }
            });
        }
    }
}
