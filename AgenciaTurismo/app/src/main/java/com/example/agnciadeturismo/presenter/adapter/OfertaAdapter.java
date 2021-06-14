package com.example.agnciadeturismo.presenter.adapter;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.model.PacoteDto;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class OfertaAdapter extends RecyclerView.Adapter<OfertaAdapter.viewHolder> {

    OnClickItemPacote listener;
    ArrayList<PacoteDto> listPacote;

    public OfertaAdapter(OnClickItemPacote listener, ArrayList<PacoteDto> listPacote) {
        this.listener = listener;
        this.listPacote = listPacote;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_oferta, parent, false);
        return new viewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PacoteDto pacote = listPacote.get(position);
        holder.textViewNome.setText(pacote.getNomePacote());
        holder.textViewValor.setText("R$"+pacote.getVlPacote());
        Picasso.get().load(new File("file://img/img.jpg")).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return listPacote.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        TextView textViewNome, textViewValor;
        ImageView img;
        public viewHolder(@NonNull View itemView, OnClickItemPacote listener) {
            super(itemView);

            textViewNome = itemView.findViewById(R.id.txt_nomeOferta);
            textViewValor = itemView.findViewById(R.id.txt_valorOferta);
            img = itemView.findViewById(R.id.img_oferta);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(getAdapterPosition());
                }
            });
        }
    }
}
