package com.delao00064815.copproyecto.ofertaEmpleo.Filtros;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.delao00064815.copproyecto.LoadData;
import com.delao00064815.copproyecto.MainActivity;
import com.delao00064815.copproyecto.ofertaEmpleo.OfertaAdapter;
import com.delao00064815.copproyecto.ofertaEmpleo.OfertaEmpleo;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by César on 25/07/2017.
 */

public class FiltroCarrera extends android.support.v4.app.DialogFragment {
    OfertaAdapter OferAdapter;
    String opciones;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle extras = getActivity().getIntent().getExtras();
       final ArrayList<CarreraClass> carrera  = extras.getParcelableArrayList("carrera");


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        System.out.println("PROBANDO ESTA WEA LOAD"+carrera.get(0).idCarrera);
        builder.setTitle("Selecciona la Carrera para la que buscas oferta ").setSingleChoiceItems((ListAdapter) carrera, -1, new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int args1) {
                opciones =  carrera.get(args1).idCarrera;
               // System.out.println("PROBANDO ESTA WEA DESPUÉS DEL CLICK"+carrera.get(args1).idCarrera);
                /*switch (args1){
                    case 0:
                        opciones = (String) carrera[args1];


                        break;
                    case 1:
                        opciones = (String) carrera[args1];

                        break;
                    case 2:
                        opciones = (String) carrera[args1];

                    break;
                    case 3:
                        opciones = (String) carrera[args1];

                        break;
                    case 4:
                        opciones = (String) carrera[args1];

                        break;
                    case 5:
                        opciones = (String) carrera[args1];

                        break;
                    default:
                        opciones = "Sin Filtro";
                        break;
                }*/
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"Filtro por carrera activado "+ opciones, Toast.LENGTH_SHORT).show();
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
