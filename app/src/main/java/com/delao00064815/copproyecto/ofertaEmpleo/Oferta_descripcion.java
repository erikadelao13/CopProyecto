package com.delao00064815.copproyecto.ofertaEmpleo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.delao00064815.copproyecto.Login;
import com.delao00064815.copproyecto.NetConection.NetOfertas;
import com.delao00064815.copproyecto.R;
import com.delao00064815.copproyecto.SessionManager;
import com.delao00064815.copproyecto.talleres.Historial;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

/**
 * Created by Manrique_Matus on 026/26/7/2017.
 */

public class Oferta_descripcion extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    TextView nombre,carrera,empresa;
    Picasso imagen;
    ImageView img;
    SessionManager session;
    NavigationView navigationView;

    String textname,textcarrera,textempresa,textimagen;
    int id;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitty_drawer_odetails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#212438"));

        nombre=(TextView)findViewById(R.id.nomOferta);
        carrera=(TextView)findViewById(R.id.nombreTipoEmpleo);
        empresa=(TextView)findViewById(R.id.remuneracion);
        img=(ImageView)findViewById(R.id.imagen);

        Bundle bundle=getIntent().getExtras();

        textname=bundle.getString("nombre");
        textcarrera=bundle.getString("carrera");
        textempresa=bundle.getString("empresa");
        textimagen=bundle.getString("img");
        id=bundle.getInt("id");

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        nombre.setText(textname);
        carrera.setText(textcarrera);
        empresa.setText(textempresa);

        imagen.with(this).load(textimagen).into(img);

        session = new SessionManager(getApplicationContext());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();

        try {
            new NetOfertas(this,id).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


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
