package com.delao00064815.copproyecto.ofertaEmpleo.Filtros;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.delao00064815.copproyecto.ofertaEmpleo.OfertaEmpleo;

/**
 * Created by César on 25/07/2017.
 */

public class FiltroTipoOferta extends android.support.v4.app.DialogFragment {
    final CharSequence[] tipoOferta = {"Pasantía", "Oferta de Empleo"};
    String opciones;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Selecciona el tipo de oferta que te interesa").setSingleChoiceItems(tipoOferta, -1, new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int args1) {
                switch (args1){
                    case 0:
                        opciones = (String) tipoOferta[args1];

                        break;
                    case 1:
                        opciones = (String) tipoOferta[args1];

                        break;
                    case 2:
                        opciones = (String) tipoOferta[args1];
                        break;
                    default:
                        opciones = "Sin filtro";
                        break;
                }
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getActivity(),"Filtro por tipo de oferta activado "+ opciones, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), OfertaEmpleo.class);
                intent.putExtra("message",opciones);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {


            } });
        return builder.create();
    }
}
