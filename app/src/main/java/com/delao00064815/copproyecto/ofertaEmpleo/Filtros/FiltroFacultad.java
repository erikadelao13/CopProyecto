package com.delao00064815.copproyecto.ofertaEmpleo.Filtros;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by César on 25/07/2017.
 */

public class FiltroFacultad extends android.support.v4.app.DialogFragment {
    final CharSequence[] facultad = {"Facultad de Ciencias Sociales y Humanidades", "Facultad de Ciencias Económicas y Empresariales", "Facultad de Ingeniería y Arquitectura"};
    String opciones;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Selecciona la Facultada para la que buscas oferta").setSingleChoiceItems(facultad, -1, new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int args1) {
                switch (args1){
                    case 0:
                        opciones = (String) facultad[args1];

                        break;
                    case 1:
                        opciones = (String) facultad[args1];

                        break;
                    case 2:
                        opciones = (String) facultad[args1];
                        break;
                    default:
                        opciones = "Sin filtro";
                        break;
                }
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"Tu facultad es: "+ opciones, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {


            } });
        return builder.create();
    }

}
