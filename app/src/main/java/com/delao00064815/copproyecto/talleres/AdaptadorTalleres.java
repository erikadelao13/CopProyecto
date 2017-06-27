package com.delao00064815.copproyecto.talleres;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import com.delao00064815.copproyecto.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rober on 24/6/2017.
 */

public class AdaptadorTalleres extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<ClaTalleres> images;

    public AdaptadorTalleres(Context context, int layout, ArrayList<ClaTalleres> images){
        this.context=context;
        this.layout=layout;
        this.images=images;
    }
    @Override
    public int getCount() {
        return this.images.size();
    }

    @Override
    public Object getItem(int position) {
        return this.images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View v = convertView;
        LayoutInflater layout_inflater = LayoutInflater.from(this.context);
        v = layout_inflater.inflate(R.layout.activity_talleres, null);
        //String currentName = images.get(position);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageTalleres);
        Picasso.with(context).load(R.drawable.oferta1).into(imageView);


        Button register = (Button) v.findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Use the Builder class for convenient dialog construction
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());

                builder.setTitle("Usted está a punto de inscribirse");

                builder.setPositiveButton("si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(parent.getContext(),"Has sido registrado correctamente",Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }});

                builder.setMessage("¿Está seguro que desea inscribirse a este taller?");
                // Create the AlertDialog object and return it
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        ImageView imageTalleres = (ImageView) v.findViewById(R.id.imageTalleres);
        imageTalleres.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                View v2 = layoutInflater.inflate(R.layout.image_view_talleres, null);
                ImageView image = (ImageView) v2.findViewById(R.id.image_view_talleres);
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                AlertDialog dialog = builder.create();
                dialog.setView(v2);
                dialog.setCancelable(true);
                image.setImageResource(R.drawable.oferta1);
                dialog.show();
            }
        });

        return v;
    }


}
