package com.delao00064815.copproyecto.directorio;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.delao00064815.copproyecto.LoadData;
import com.delao00064815.copproyecto.R;
import com.delao00064815.copproyecto.ofertaEmpleo.*;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by hmanr on 27/6/2017.
 */

public class DAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<DirectorioClass> direct;

    public DAdapter(Context context, int layout, ArrayList<DirectorioClass> direct){
        this.context=context;
        this.layout=layout;
        this.direct=direct;
    }
    @Override
    public int getCount() {
        return this.direct.size();
    }

    @Override
    public Object getItem(int position) {
        return this.direct.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(R.layout.directory_content,null);
        TextView name= (TextView) convertView.findViewById(R.id.txt_nombre);
        TextView email= (TextView) convertView.findViewById(R.id.txt_correo);
        TextView charge= (TextView) convertView.findViewById(R.id.txt_cargo);
        name.setText(direct.get(position).getNombreEmpleado());
        email.setText(direct.get(position).getCorreoEmpleado());
        charge.setText(direct.get(position).getCargo());


        return convertView;

    }
}
