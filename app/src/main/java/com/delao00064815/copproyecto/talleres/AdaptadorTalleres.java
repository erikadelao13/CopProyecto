package com.delao00064815.copproyecto.talleres;

import android.content.Context;
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
    private List<String> images;

    public AdaptadorTalleres(Context context, int layout, List<String> images){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater layout_inflater = LayoutInflater.from(this.context);
        v = layout_inflater.inflate(R.layout.activity_talleres, null);
        String currentName = images.get(position);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageTalleres);
        Picasso.with(context).load(R.drawable.fullofficial).into(imageView);

/*
        Button register = (Button) v.findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View mView = context.getLayoutInflater().inflate(R.layout.custom_dialog,null);
                Button aceptar = (Button) v.findViewById(R.id.aceptar);
                aceptar.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Has sido registrado correctamente.",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                builder.setView(mView);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });*/

        return v;
    }


}
