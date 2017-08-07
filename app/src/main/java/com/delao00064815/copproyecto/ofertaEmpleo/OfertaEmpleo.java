package com.delao00064815.copproyecto.ofertaEmpleo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.delao00064815.copproyecto.LoadData;
import com.delao00064815.copproyecto.Login;
import com.delao00064815.copproyecto.NetConection.NetOfertas;
import com.delao00064815.copproyecto.R;
import com.delao00064815.copproyecto.ofertaEmpleo.Filtros.FiltroCarrera;
import com.delao00064815.copproyecto.ofertaEmpleo.Filtros.FiltroFacultad;
import com.delao00064815.copproyecto.ofertaEmpleo.Filtros.FiltroTipoOferta;
import com.github.clans.fab.FloatingActionMenu;

import java.util.concurrent.ExecutionException;

/**
 * Created by hmanr on 5/6/2017.
 */

public class OfertaEmpleo extends FragmentActivity {
    //MaterialSearchView searchView;
    OfertaAdapter oAdapter;
    RecyclerView rView;
    FloatingActionMenu actionMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ofertas_empleo);
        actionMenu=(FloatingActionMenu)findViewById(R.id.fabprincipal);
        actionMenu.setClosedOnTouchOutside(true);

        rView=(RecyclerView)findViewById(R.id.mRecycler);

        try {
            new LoadData(this,oAdapter,rView,"oferta").execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        /*try {
            new NetOfertas(this,oAdapter,rView).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        /*searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

                //If closed Search View , lstView will return default
                //lstView = (ListView)findViewById(R.id.lstView);
                //ArrayAdapter adapter = new ArrayAdapter(OfertaEmpleo.this,android.R.layout.simple_list_item_1,lstSource);
               // lstView.setAdapter(adapter);

            }
        });*/

        /*searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != null && !newText.isEmpty()){
                    //List<String> lstFound = new ArrayList<String>();
                    for(String item:lstSource){
                        if(item.contains(newText))
                            lstFound.add(item);
                    }

                   ArrayAdapter adapter = new ArrayAdapter(OfertaEmpleo.this,android.R.layout.simple_list_item_1,lstFound);
                    lstView.setAdapter(adapter);
                }
                else{
                    //if search text is null
                    //return default
                    ArrayAdapter adapter = new ArrayAdapter(OfertaEmpleo.this,android.R.layout.simple_list_item_1,lstSource);
                    lstView.setAdapter(adapter);
                }
                return true;
            }

        });*/
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }
*/
    //@Override
    public void selectCarrera(View v){
            FiltroCarrera my_dialog = new FiltroCarrera();
            my_dialog.show(getSupportFragmentManager(),"Dialog Carrera");
            actionMenu=(FloatingActionMenu)findViewById(R.id.fabprincipal);
            actionMenu.toggle(true);
    }
    public void selectTipo(View v){
        FiltroTipoOferta my_dialog = new FiltroTipoOferta();
        my_dialog.show(getSupportFragmentManager(),"Dialog Tipo");
        actionMenu=(FloatingActionMenu)findViewById(R.id.fabprincipal);
        actionMenu.toggle(true);
    }
    public void selectFacultad(View v){
        FiltroFacultad my_dialog = new FiltroFacultad();
        my_dialog.show(getSupportFragmentManager(),"Dialog Facultad");
        actionMenu=(FloatingActionMenu)findViewById(R.id.fabprincipal);
        actionMenu.toggle(true);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int res_id = item.getItemId();
        if(res_id==R.id.login){
            Intent i = new Intent(this, Login.class);
            startActivity(i);
        }
        return true;
    }
    /*public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        //MenuItem item = menu.findItem(R.id.action_search);
        //searchView.setMenuItem(item);
        return true;
    }*/
}
