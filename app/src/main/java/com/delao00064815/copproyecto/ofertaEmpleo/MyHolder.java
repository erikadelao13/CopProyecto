package com.delao00064815.copproyecto.ofertaEmpleo;

/**
 * Created by César on 18/06/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.delao00064815.copproyecto.R;


public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView title, content, lastDate;
    ImageView imagee;
    LinearLayout but;
    ItemClickListener itemClickListener;

    public MyHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        content = (TextView) itemView.findViewById(R.id.content2);
        lastDate = (TextView) itemView.findViewById(R.id.lastDate);
        imagee = (ImageView) itemView.findViewById(R.id.imageO);
        but=(LinearLayout) itemView.findViewById(R.id.cardview);
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
