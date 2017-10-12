package com.delao00064815.copproyecto.ofertaEmpleo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.delao00064815.copproyecto.LoadData;
import com.delao00064815.copproyecto.Login;
import com.delao00064815.copproyecto.NetConection.NetOfertas;
import com.delao00064815.copproyecto.R;
import com.delao00064815.copproyecto.SessionManager;
import com.delao00064815.copproyecto.ofertaEmpleo.Filtros.FiltroCarrera;
import com.delao00064815.copproyecto.ofertaEmpleo.Filtros.FiltroFacultad;
import com.delao00064815.copproyecto.ofertaEmpleo.Filtros.FiltroTipoOferta;
import com.delao00064815.copproyecto.talleres.Historial;
import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;
import java.util.concurrent.ExecutionException;

/**
 * Created by hmanr on 5/6/2017.
 */

public class OfertaEmpleo extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener {
    //MaterialSearchView searchView;
    OfertaAdapter oAdapter;
    RecyclerView rView;
    FloatingActionMenu actionMenu;
    FloatingActionButton actionSinfiltro;
    SessionManager session;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_ofertas);
        actionMenu=(FloatingActionMenu)findViewById(R.id.fabprincipal);
        actionMenu.setClosedOnTouchOutside(true);
        actionSinfiltro =(com.github.clans.fab.FloatingActionButton)findViewById(R.id.sinfiltro_float_button);
        String tipoFiltro = getIntent().getStringExtra("message");
        rView=(RecyclerView)findViewById(R.id.mRecycler);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        session = new SessionManager(getApplicationContext());
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
                    new LoadData(this,oAdapter,rView,"ofertaCarrera","http://copuca-com.stackstaging.com/WebServer/oferta_empleocarrera.php?carrera=3160").execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            case "Ing. Quimica":
                try {
                    actionSinfiltro.setVisibility(View.VISIBLE);
                    new LoadData(this,oAdapter,rView,"ofertaCarrera","http://copuca-com.stackstaging.com/WebServer/oferta_empleocarrera.php?carrera=3050").execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            case "Ing. Industrial":
                try {
                    actionSinfiltro.setVisibility(View.VISIBLE);
                    new LoadData(this,oAdapter,rView,"ofertaCarrera","http://copuca-com.stackstaging.com/WebServer/oferta_empleocarrera.php?carrera=3040").execute().get();
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
                    new LoadData(this,oAdapter,rView,"ofertaCarrera","http://copuca-com.stackstaging.com/WebServer/oferta_empleocarrera.php?carrera=3010").execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            case "Licenciatura en Mercadeo":
                try {
                    actionSinfiltro.setVisibility(View.VISIBLE);
                    new LoadData(this,oAdapter,rView,"ofertaCarrera","http://copuca-com.stackstaging.com/WebServer/oferta_empleocarrera.php?carrera=2220").execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            case "Leyes":
                try {
                    actionSinfiltro.setVisibility(View.VISIBLE);
                    new LoadData(this,oAdapter,rView,"ofertaCarrera","http://copuca-com.stackstaging.com/WebServer/oferta_empleocarrera.php?carrera=1260").execute().get();
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
    public void selectSinFiltro(View v){
        Intent intent = new Intent(this, OfertaEmpleo.class);
        intent.putExtra("message","jobs");
        startActivity(intent);
        actionMenu=(FloatingActionMenu)findViewById(R.id.fabprincipal);
        actionMenu.toggle(true);
    }
    /*public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        //MenuItem item = menu.findItem(R.id.action_search);
        //searchView.setMenuItem(item);
        return true;
    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        String msg2;
        int id = item.getItemId();

        super.onResume();
        if(session.isLoggedIn()){
            navigationView.getMenu().findItem(R.id.item1).setVisible(false);
            navigationView.getMenu().findItem(R.id.item5).setVisible(true);
        } else {
            navigationView.getMenu().findItem(R.id.item1).setVisible(true);
            navigationView.getMenu().findItem(R.id.item5).setVisible(false);
        }

        if (id == R.id.item1) {
            Intent intent=new Intent(this,Login.class);
            startActivity(intent);
        } else if (id == R.id.item2) {

            Intent intent4 = new Intent(this,Historial.class);
            msg2 = "workshop";
            intent4.putExtra("message", msg2);
            startActivity(intent4);

        } else if (id == R.id.item5) {
            Toast.makeText(this, "Sesion Cerrada",Toast.LENGTH_LONG).show();
            session.logoutUser();
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void onResume()
    {
        super.onResume();
        if(session.isLoggedIn()){
            navigationView.getMenu().findItem(R.id.item1).setVisible(false);
            navigationView.getMenu().findItem(R.id.item5).setVisible(true);
        } else {
            navigationView.getMenu().findItem(R.id.item1).setVisible(true);
            navigationView.getMenu().findItem(R.id.item5).setVisible(false);
        }
    }

}
