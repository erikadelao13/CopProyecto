package com.delao00064815.copproyecto.ofertaEmpleo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.delao00064815.copproyecto.NetConection.NetOfertas;
import com.delao00064815.copproyecto.R;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

/**
 * Created by Manrique_Matus on 026/26/7/2017.
 */

public class Oferta_descripcion extends AppCompatActivity {
    TextView nombre,carrera,empresa;
    Picasso imagen;
    ImageView img;

    String textname,textcarrera,textempresa,textimagen;
    int id;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oferta_details);

        nombre=(TextView)findViewById(R.id.nomOferta);
        carrera=(TextView)findViewById(R.id.nombreTipoEmpleo);
        empresa=(TextView)findViewById(R.id.remuneracion);
        img=(ImageView)findViewById(R.id.imagen);

        Bundle bundle=getIntent().getExtras();

        textname=bundle.getString("nombre");
        textcarrera=bundle.getString("carrera");
        textempresa=bundle.getString("empresa");
        textimagen=bundle.getString("img");
        id=bundle.getInt("id");

        nombre.setText(textname);
        carrera.setText(textcarrera);
        empresa.setText(textempresa);

        imagen.with(this).load(textimagen).into(img);

        try {
            new NetOfertas(this,id).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }



}
