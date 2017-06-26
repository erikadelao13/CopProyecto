package com.delao00064815.copproyecto.directorio;

/**
 * Created by César on 18/06/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.delao00064815.copproyecto.R;
import com.delao00064815.copproyecto.directorio.ItemClickListener;


public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView nombre, correo,cargo;
    ItemClickListener itemClickListener;
    public MyHolder(View itemView) {
        super(itemView);

        nombre= (TextView) itemView.findViewById(R.id.txt_nombre);
        correo= (TextView) itemView.findViewById(R.id.txt_correo);
        cargo= (TextView) itemView.findViewById(R.id.txt_cargo);
        itemView.setOnClickListener(this);

    }
    public  void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(getLayoutPosition());
    }
}
