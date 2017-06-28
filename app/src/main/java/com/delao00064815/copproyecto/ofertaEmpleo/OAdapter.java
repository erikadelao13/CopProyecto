package com.delao00064815.copproyecto.ofertaEmpleo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.delao00064815.copproyecto.R;
import com.delao00064815.copproyecto.directorio.DirectorioClass;

import java.util.List;

/**
 * Created by hmanr on 27/6/2017.
 */

public class OAdapter extends ArrayAdapter<OfertaClass> {
    public OAdapter(Context context, List<OfertaClass> direct) {
        super(context,0, direct);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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

        OfertaClass o=getItem(position);
        title.setText(o.getnomTipoOferta());
        carreer.setText(o.getCarrera());
        finalDate.setText(o.getFechaLimite());
        return convertView;
    }
}
