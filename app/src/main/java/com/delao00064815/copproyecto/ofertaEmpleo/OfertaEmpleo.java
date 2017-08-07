package com.delao00064815.copproyecto.ofertaEmpleo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.github.clans.fab.FloatingActionButton;
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

public class OfertaEmpleo  extends AppCompatActivity {
    //MaterialSearchView searchView;
    OAdapter oAdapter;
    ListView rView;
    FloatingActionMenu actionMenu;
    FloatingActionButton actionSinfiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ofertas_empleo);
        actionMenu=(FloatingActionMenu)findViewById(R.id.fabprincipal);
        actionMenu.setClosedOnTouchOutside(true);
        actionSinfiltro =(FloatingActionButton)findViewById(R.id.sinfiltro_float_button);
        String tipoFiltro = getIntent().getStringExtra("message");
        rView=(ListView)findViewById(R.id.mRecycler);
        switch(tipoFiltro){
            case "jobs":
                try {
                    actionSinfiltro.setVisibility(View.GONE);
                    new LoadData(this,oAdapter,rView,"oferta").execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            case "Ing. Informática":
                try {
                    actionSinfiltro.setVisibility(View.VISIBLE);
                    new LoadData(this,oAdapter,rView,"ofertaCarrera","http://copuca-com.stackstaging.com/WebServer/oferta_empleocarrera.php?carrera=Ingenieria%20Informatica").execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            case "Ing. Quimica":
                try {
                    actionSinfiltro.setVisibility(View.VISIBLE);
                    new LoadData(this,oAdapter,rView,"ofertaCarrera","http://copuca-com.stackstaging.com/WebServer/oferta_empleocarrera.php?carrera=Ingenieria%20Quimica").execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            case "Ing. Industrial":
                try {
                    actionSinfiltro.setVisibility(View.VISIBLE);
                    new LoadData(this,oAdapter,rView,"ofertaCarrera","http://copuca-com.stackstaging.com/WebServer/oferta_empleocarrera.php?carrera=Ingenieria%20Industrial").execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            case "Pasantía":
                try {
                    actionSinfiltro.setVisibility(View.VISIBLE);
                    new LoadData(this,oAdapter,rView,"ofertaTipo","http://copuca-com.stackstaging.com/WebServer/oferta_empleotipo.php?tipoofer=Pasantia").execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            case "Oferta de Empleo":
                try {
                    actionSinfiltro.setVisibility(View.VISIBLE);
                    new LoadData(this,oAdapter,rView,"ofertaTipo","http://copuca-com.stackstaging.com/WebServer/oferta_empleotipo.php?tipoofer=Plaza%20Fija").execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            case "Ing. Electrica":
                try {
                    actionSinfiltro.setVisibility(View.VISIBLE);
                    new LoadData(this,oAdapter,rView,"ofertaCarrera","http://copuca-com.stackstaging.com/WebServer/oferta_empleocarrera.php?carrera=Ingenieria%20Electrica").execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            case "Licenciatura en Mercadeo":
                try {
                    actionSinfiltro.setVisibility(View.VISIBLE);
                    new LoadData(this,oAdapter,rView,"ofertaCarrera","http://copuca-com.stackstaging.com/WebServer/oferta_empleocarrera.php?carrera=Licenciatura%20en%20Mercadeo").execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            case "Leyes":
                try {
                    actionSinfiltro.setVisibility(View.VISIBLE);
                    new LoadData(this,oAdapter,rView,"ofertaCarrera","http://copuca-com.stackstaging.com/WebServer/oferta_empleocarrera.php?carrera=Leyes").execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            default:


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
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        if(pref.contains("carnetE")){
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.menu_session,menu);
            MenuItem menuItem = (MenuItem) findViewById(R.id.sesion);
            menuItem.setTitle("sesion iniciada como: "+pref.getString("carnetE",null));
        } else {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int res_id = item.getItemId();
        if(res_id==R.id.login){
            Intent i = new Intent(this, Login.class);
            startActivity(i);
        }
        /*else if(){

        }*/
        return true;
    }

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
        Intent intent = new Intent(this, OfertaEmpleo.class);
        intent.putExtra("message","jobs");
        startActivity(intent);
        actionMenu=(FloatingActionMenu)findViewById(R.id.fabprincipal);
        actionMenu.toggle(true);
    }

}
