package com.delao00064815.copproyecto.talleres;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.delao00064815.copproyecto.LoadData;
import com.squareup.picasso.Picasso;

import com.delao00064815.copproyecto.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import uk.co.senab.photoview.PhotoViewAttacher;

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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View v = convertView;
        LayoutInflater layout_inflater = LayoutInflater.from(this.context);
        v = layout_inflater.inflate(R.layout.activity_talleres, null);
        //String currentName = images.get(position);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageTalleres);
        Picasso.with(context).load(images.get(position).getImgTaller()).into(imageView);


        final Button register = (Button) v.findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences pref = context.getApplicationContext().getSharedPreferences("MyPref", 0);
                if(!pref.contains("carnetE")) {
                    Toast.makeText(parent.getContext(),"Debes iniciar sesión para poder registrarte a un taller",Toast.LENGTH_SHORT).show();
                    return;
                }
                // Use the Builder class for convenient dialog construction
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());

                builder.setTitle("Usted está a punto de inscribirse");

                builder.setPositiveButton("si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            new LoadData(parent.getContext(), String.valueOf(images.get(position).getIdTaller())).execute().get();
                            Log.d("taunty", String.valueOf(images.get(position).getIdTaller()));
                            register.setVisibility(View.INVISIBLE);
                            Toast.makeText(parent.getContext(),"Has sido registrado correctamente",Toast.LENGTH_SHORT).show();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
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

        final ImageView imageTalleres = (ImageView) v.findViewById(R.id.imageTalleres);
        imageTalleres.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                View v2 = layoutInflater.inflate(R.layout.image_view_talleres, null);
                ImageView image = (ImageView) v2.findViewById(R.id.image_view_talleres);
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                //con esto cargo las imagenes desde el webservice
                PhotoViewAttacher photoView = new PhotoViewAttacher(image); //esto uso para el zoom de las imagenes y otras cosas que agregue al gradle
                Picasso.with(context).load(images.get(position).getImgTaller()).into(image);
                AlertDialog dialog = builder.create();
                dialog.setView(v2);
                dialog.setCancelable(true);
                //image.setImageResource(R.drawable.oferta1);
                dialog.show();
            }
        });

        return v;
    }


}
