package com.delao00064815.copproyecto.ofertaEmpleo;

/**
 * Created by César on 18/06/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tituloTxt, subtituloTxt,imgNoticiaTxt,idNoticiaTxt, idJuegoTxt,descNoticiaTxt;
    ItemClickListener itemClickListener;
    public MyHolder(View itemView) {
        super(itemView);
        /*
       tituloTxt= (TextView) itemView.findViewById(R.id.titulotxt);
        subtituloTxt= (TextView) itemView.findViewById(R.id.subtitulotxt);
        imgNoticiaTxt= (TextView) itemView.findViewById(R.id.imgnoticiatxt);
        idNoticiaTxt= (TextView) itemView.findViewById(R.id.idnoticiatxt);
        idJuegoTxt= (TextView) itemView.findViewById(R.id.idjuegotxt);
        descNoticiaTxt= (TextView) itemView.findViewById(R.id.descNoticiatxt);
        itemView.setOnClickListener(this);
        */
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
