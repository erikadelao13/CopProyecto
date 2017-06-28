package com.delao00064815.copproyecto.ofertaEmpleo;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.delao00064815.copproyecto.R;
import com.delao00064815.copproyecto.directorio.DirectorioClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

import static com.delao00064815.copproyecto.R.id.imageView;

/**
 * Created by hmanr on 27/6/2017.
 */

public class OAdapter extends ArrayAdapter<OfertaClass> {
    Context c;
    List<OfertaClass> oferta;
    public OAdapter(Context context,List<OfertaClass> direct) {
        super(context,0, direct);
        this.c=context;
        this.oferta=direct;
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
        ImageView img=(ImageView)convertView.findViewById(R.id.imageO);
        TextView title=(TextView)convertView.findViewById(R.id.title);
        TextView carreer=(TextView)convertView.findViewById(R.id.content2);
        TextView finalDate=(TextView)convertView.findViewById(R.id.lastDate);
        TextView empire=(TextView)convertView.findViewById(R.id.content);

        OfertaClass o=getItem(position);
        title.setText(o.getnomTipoOferta());
        carreer.setText(o.getCarrera());
        finalDate.setText(o.getCargo());
        empire.setText(o.getEmpresa());
        Picasso.with(c).load(o.getImg()).into(img);

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
        return convertView;
    }
}
