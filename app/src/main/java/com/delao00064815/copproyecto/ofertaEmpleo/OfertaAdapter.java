package com.delao00064815.copproyecto.ofertaEmpleo;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.delao00064815.copproyecto.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

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
    public void onBindViewHolder(MyHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: "+ofertas.get(position).getNomTipoOferta()+"");
        /*holder.title.setText(ofertas.get(position).getNomTipoOferta());
        holder.content.setText(ofertas.get(position).getCarrera());
        holder.lastDate.setText(ofertas.get(position).getFechaLimite());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                //Toast.makeText(c,players.get(pos).getIdNoticia(),Toast.LENGTH_SHORT).show();
            }
        });*/
        final OfertaClass o=ofertas.get(position);
        holder.title.setText(o.getNomTipoOferta());
        holder.lastDate.setText(o.getCargo());
        holder.content.setText(o.getCarrera());
        Picasso.with(c).load(o.getImg()).into(holder.imagee);
        holder.imagee.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(c);
                View v2 = layoutInflater.inflate(R.layout.image_view_ofertas, null);
                ImageView image = (ImageView) v2.findViewById(R.id.image_view_ofertas);
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                AlertDialog dialog = builder.create();
                dialog.setView(v2);
                dialog.setCancelable(true);
                PhotoViewAttacher photoView = new PhotoViewAttacher(image); //esto uso para el zoom de las imagenes y otras cosas que agregue al gradle
                Picasso.with(c).load(ofertas.get(position).getImg()).into(image);
                dialog.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return ofertas.size();
    }

    }