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
    private List<Talleres> images;

    public AdaptadorTalleres(Context context, int layout, List<Talleres> images){
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        LayoutInflater layout_inflater = LayoutInflater.from(this.context);
        v = layout_inflater.inflate(R.layout.activity_talleres, null);
        Talleres currentName = images.get(position);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageTalleres);
        //imageView.setImageDrawable();
        return v;
    }


}