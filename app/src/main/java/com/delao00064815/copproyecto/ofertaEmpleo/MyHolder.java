package com.delao00064815.copproyecto.ofertaEmpleo;

/**
 * Created by César on 18/06/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.snowdream.android.widget.SmartImageView;
import com.delao00064815.copproyecto.R;


public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView titulo, carrera;
    SmartImageView foto;
    ItemClickListener itemClickListener;
    public MyHolder(View itemView) {
        super(itemView);
        titulo= (TextView) itemView.findViewById(R.id.title);
        carrera= (TextView) itemView.findViewById(R.id.content2);
        foto= (SmartImageView) itemView.findViewById(R.id.imagee);

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
