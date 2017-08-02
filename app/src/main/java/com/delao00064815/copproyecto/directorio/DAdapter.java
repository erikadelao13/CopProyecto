package com.delao00064815.copproyecto.directorio;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ListView;
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
    public View getView(final int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(R.layout.directory_content,null);
        TextView name= (TextView) convertView.findViewById(R.id.txt_nombre);
        TextView email= (TextView) convertView.findViewById(R.id.txt_correo);
        TextView charge= (TextView) convertView.findViewById(R.id.txt_cargo);
        name.setText(direct.get(position).getNombreEmpleado());
        email.setText(direct.get(position).getCorreoEmpleado());
        charge.setText(direct.get(position).getCargo());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{direct.get(position).getCorreoEmpleado()});
                intent.setPackage("com.google.android.gm");
                if (intent.resolveActivity(context.getPackageManager())!=null)
                    context.startActivity(intent);
                else
                    Toast.makeText(context,"Gmail App is not installed",Toast.LENGTH_SHORT).show();//Este es para escoger con que aplicacion deseo abrir el correo, yo solo lo quiero con gmail, pero le doy a escoger asi porque el package puede cambiar de nombre y crashear
            }
        });

        return convertView;

    }
}
