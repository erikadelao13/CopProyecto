package com.delao00064815.copproyecto.ofertaEmpleo.Filtros;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

import com.delao00064815.copproyecto.LoadData;
import com.delao00064815.copproyecto.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by César on 31/10/2017.
 */

public class enlace  extends FragmentActivity{
    ArrayList<CarreraClass> carrera;
    FloatingActionMenu actionMenu;
    FloatingActionButton actionSinfiltro;

    public enlace(ArrayList<CarreraClass> carrera) {
        this.carrera = carrera;
    }
    //@Override
    public void selectCarrera(View v){
// set Fragmentclass Arguments
        FiltroCarrera my_dialog = new FiltroCarrera();
        //Bundle extras = this.getIntent().getExtras();
        // ArrayList<CarreraClass> carrera  = extras.getParcelableArrayList("carrera");
        // System.out.println("PROBANDO ESTA WEA OFERTA"+carrera.get(0).idCarrera);
        Bundle bundle = new Bundle();
        System.out.println("PROBANDO en ENLACE "+ carrera.get(0).idCarrera);
        bundle.putParcelableArrayList("carrera", carrera);
        my_dialog.setArguments(bundle);
        //my_dialog.show(getSupportFragmentManager(),"Dialog Carrera");
        actionMenu=(FloatingActionMenu)findViewById(R.id.fabprincipal);
        actionMenu.toggle(true);
    }



}
