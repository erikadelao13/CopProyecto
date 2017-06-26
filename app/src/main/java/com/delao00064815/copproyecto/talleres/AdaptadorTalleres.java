package com.delao00064815.copproyecto.talleres;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import com.delao00064815.copproyecto.R;

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
        Picasso.with(context).load(R.drawable.oferta1).into(imageView);
        return v;
    }


}
