package com.delao00064815.copproyecto.directorio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delao00064815.copproyecto.R;

import java.util.ArrayList;

public class DirectAdapter extends RecyclerView.Adapter<com.delao00064815.copproyecto.directorio.MyHolder> {
    Context c;
    ArrayList<DirectorioClass> players;
    public DirectAdapter(Context c, ArrayList<DirectorioClass> players) {
        this.c = c;
        this.players = players;
    }
    @Override
    public com.delao00064815.copproyecto.directorio.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.ofertas_empleo,parent,false);
        com.delao00064815.copproyecto.directorio.MyHolder holder=new com.delao00064815.copproyecto.directorio.MyHolder(v);
        return holder;
    }
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        /*holder.tituloTxt.setText(players.get(position).getTitulo());
        holder.subtituloTxt.setText(players.get(position).getSubtitulo());
        holder.imgNoticiaTxt.setText(players.get(position).getImgNoticia());
        holder.idJuegoTxt.setText(Integer.toString(players.get(position).getIdJuego()));
        holder.idNoticiaTxt.setText(Integer.toString(players.get(position).getIdNoticia()));
        holder.descNoticiaTxt.setText(players.get(position).getDescNoticia());*/
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                //Toast.makeText(c,players.get(pos).getIdNoticia(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return players.size();
    }
}