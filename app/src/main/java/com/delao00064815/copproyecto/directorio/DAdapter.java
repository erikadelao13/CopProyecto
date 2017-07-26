package com.delao00064815.copproyecto.directorio;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.delao00064815.copproyecto.R;
import com.delao00064815.copproyecto.ofertaEmpleo.*;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by hmanr on 27/6/2017.
 */

public class DAdapter extends ArrayAdapter<DirectorioClass> {
    public DAdapter(Context context, List<DirectorioClass> direct) {
        super(context,0, direct);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.directory_content,
                    parent,
                    false);
        }
        TextView name=(TextView)convertView.findViewById(R.id.txt_nombre);
        TextView email=(TextView)convertView.findViewById(R.id.txt_correo);
        TextView charge=(TextView)convertView.findViewById(R.id.txt_cargo);

        DirectorioClass d=getItem(position);

        name.setText(d.getNombreEmpleado());
        email.setText(d.getCorreoEmpleado());
        charge.setText(d.getCargo());

        return convertView;
    }

}
