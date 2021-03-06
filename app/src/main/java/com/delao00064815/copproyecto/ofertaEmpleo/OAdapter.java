package com.delao00064815.copproyecto.ofertaEmpleo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.delao00064815.copproyecto.R;
import com.delao00064815.copproyecto.directorio.DirectorioClass;
import com.delao00064815.copproyecto.ofertaEmpleo.Filtros.CarreraClass;
import com.delao00064815.copproyecto.ofertaEmpleo.Filtros.FiltroCarrera;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

import static com.delao00064815.copproyecto.R.id.cardview;
import static com.delao00064815.copproyecto.R.id.imageView;

/**
 * Created by hmanr on 27/6/2017.
 */

public class OAdapter extends ArrayAdapter<OfertaClass> {
    //no se usa borrar
    Context c;
    List<OfertaClass> oferta;
    ArrayList<CarreraClass> carrera;
    FloatingActionMenu actionMenu;
    FloatingActionButton actionSinfiltro;
    public OAdapter(Context context,List<OfertaClass> direct) {
        super(context,0, direct);
        this.c=context;
        this.oferta=direct;
    }

    public OAdapter(@NonNull Context context, @LayoutRes int resource, Context c, List<OfertaClass> oferta, ArrayList<CarreraClass> carrera) {
        super(context, resource);
        this.c = c;
        this.oferta = oferta;
        this.carrera = carrera;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (null == convertView) {
            convertView = inflater.inflate(
                R.layout.oferta_content,
                parent,
                false);
    }
        actionMenu=(FloatingActionMenu)convertView.findViewById(R.id.fabprincipal);

        actionSinfiltro =(com.github.clans.fab.FloatingActionButton)convertView.findViewById(R.id.sinfiltro_float_button);
        ImageView img=(ImageView)convertView.findViewById(R.id.imageO);
        TextView title=(TextView)convertView.findViewById(R.id.title);
        TextView carreer=(TextView)convertView.findViewById(R.id.content2);
        TextView finalDate=(TextView)convertView.findViewById(R.id.lastDate);
        TextView empire=(TextView)convertView.findViewById(R.id.content);
        LinearLayout but=(LinearLayout)convertView.findViewById(R.id.cardview);
        actionMenu.setClosedOnTouchOutside(true);
        OfertaClass o=getItem(position);
        title.setText(o.getNomTipoOferta());
        carreer.setText(o.getCarrera());
        finalDate.setText(o.getCargo());
        empire.setText(o.getEmpresa());
        Picasso.with(c).load(o.getImg()).into(img);

    //El error
        /*but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(c,Oferta_descripcion.class);
                intent.putExtra("id",oferta.get(position).getIdOferta());
                intent.putExtra("nombre",oferta.get(position).getNomTipoOferta());
                intent.putExtra("carrera",oferta.get(position).getCarrera());
                intent.putExtra("img",oferta.get(position).getImg());
                intent.putExtra("empresa",oferta.get(position).getEmpresa());
                c.startActivity(intent);
            }

        });*/

        ImageView imageTalleres = (ImageView)convertView.findViewById(R.id.imageO);
        imageTalleres.setOnClickListener(new View.OnClickListener(){
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
                Picasso.with(c).load(oferta.get(position).getImg()).into(image);
                dialog.show();
            }
        });
        /*second_image_ofertas*/
        LinearLayout secondImageOfertas = (LinearLayout)convertView.findViewById(R.id.second_image_ofertas);
        secondImageOfertas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(c);
                View v2 = layoutInflater.inflate(R.layout.ofertas_detail, null);
                ImageView image = (ImageView) v2.findViewById(R.id.second_image_view_ofertas);
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                AlertDialog dialog = builder.create();
                dialog.setView(v2);
                dialog.setCancelable(true);
                PhotoViewAttacher photoView = new PhotoViewAttacher(image); //esto uso para el zoom de las imagenes y otras cosas que agregue al gradle
                Picasso.with(c).load(oferta.get(position).getImg_detail()).into(image);
                dialog.show();
            }
        });
        return convertView;
    }

}
