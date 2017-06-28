package com.delao00064815.copproyecto.ofertaEmpleo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.delao00064815.copproyecto.R;

import java.util.ArrayList;

public class OfertaAdapter extends RecyclerView.Adapter<MyHolder> {
    String TAG="Lo que sea";
    Context c;
    ArrayList<OfertaClass> ofertas;
    public OfertaAdapter(Context c, ArrayList<OfertaClass> players) {
        this.c = c;
        this.ofertas = players;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.oferta_content,parent,false);
        MyHolder holder=new MyHolder(v);
        return holder;
    }
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        /*holder.tituloTxt.setText(players.get(position).getIdOferta());
        holder.subtituloTxt.setText(players.get(position).getnomTipoOferta());
        holder.imgNoticiaTxt.setText(players.get(position).getEmpresa());
        holder.idJuegoTxt.setText(players.);
        holder.idNoticiaTxt.setText(Integer.toString(players);
        holder.descNoticiaTxt.setText(players.get(position).getDescNoticia());*/
        Log.d(TAG, "onBindViewHolder: "+ofertas.get(position).getnomTipoOferta()+"");
        holder.title.setText(ofertas.get(position).getnomTipoOferta());
        holder.content.setText(ofertas.get(position).getCarrera());
        holder.lastDate.setText(ofertas.get(position).getFechaLimite());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                //Toast.makeText(c,players.get(pos).getIdNoticia(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return ofertas.size();
    }
}