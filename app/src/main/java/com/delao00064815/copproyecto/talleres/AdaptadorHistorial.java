package com.delao00064815.copproyecto.talleres;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.delao00064815.copproyecto.LoadData;
import com.delao00064815.copproyecto.R;
import com.delao00064815.copproyecto.SessionManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by rober on 24/6/2017.
 */

public class AdaptadorHistorial extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<ClaTalleres> images;

    SessionManager session;

    public AdaptadorHistorial(Context context, int layout, ArrayList<ClaTalleres> images){
        this.context=context;
        this.layout=layout;
        this.images=images;
        session = new SessionManager(context);
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
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View v = convertView;
        LayoutInflater layout_inflater = LayoutInflater.from(this.context);
        v = layout_inflater.inflate(R.layout.activity_historial, null);
        //String currentName = images.get(position);
        if(images.isEmpty()){
            TextView empire=(TextView)convertView.findViewById(R.id.txtdescHist);
            empire.setText("No te has inscrito a un taller todavía");
        }
        else{
            ImageView imageView = (ImageView) v.findViewById(R.id.imageHistorial);
            Picasso.with(context).load(images.get(position).getImgTaller()).into(imageView);
            TextView title=(TextView) v.findViewById(R.id.txtdescHist);
            TextView sub = (TextView) v.findViewById(R.id.txttitHist);
            title.setText(images.get(position).getNomTaller());
            sub.setText(images.get(position).getFechaTaller());

        }
        System.out.println("EL RESULTADO DE IMAGES ES" + images.isEmpty());
        final ImageView imageTalleres = (ImageView) v.findViewById(R.id.imageHistorial);
        imageTalleres.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                View v2 = layoutInflater.inflate(R.layout.image_view_historial, null);
                ImageView image = (ImageView) v2.findViewById(R.id.image_view_historial);
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(parent.getContext(),android.R.style.Theme_Black_NoTitleBar_Fullscreen);
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
