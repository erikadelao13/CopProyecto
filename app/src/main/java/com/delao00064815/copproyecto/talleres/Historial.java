package com.delao00064815.copproyecto.talleres;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.delao00064815.copproyecto.LoadData;
import com.delao00064815.copproyecto.Login;
import com.delao00064815.copproyecto.MainActivity;
import com.delao00064815.copproyecto.R;
import com.delao00064815.copproyecto.SessionManager;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.delao00064815.copproyecto.SessionManager.KEY_CARNET;

/**
 * Created by hmanr on 5/6/2017.
 */

public class Historial extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    Button register;
    ListView listView;
    ArrayList<String> images;
    AdaptadorHistorial myAdapter;
    SessionManager session;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        setContentView(R.layout.activity_drawer_historial);
        //aqui deberia sacar la lista de la base de datos supongo
        TextView mTextView = (TextView) findViewById(R.id.txtInscripcion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#212438"));
// Put this in OnCreate

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        images = new ArrayList<String>() {
        };
        listView = (ListView) findViewById(R.id.listViewH);

        try {
            new LoadData(this,myAdapter,listView,"historialUser",String.valueOf(session.getUserDetails().get(KEY_CARNET))/*String.valueOf(session.getUserDetails())"00025815"*/).execute().get();
        } catch (InterruptedException e) {
            mTextView.setText("No te has inscrito a nada todavía");
            e.printStackTrace();
        } catch (ExecutionException e) {
            mTextView.setText("No te has inscrito a nada todavía");
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            //code
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        String msg2;
        int id = item.getItemId();

        if (id == R.id.item1) {
            Intent intent=new Intent(this,Login.class);
            startActivity(intent);
        } else if (id == R.id.item2) {
            Log.d("prueba", "rip");
            Intent intent4 = new Intent(this,Historial.class);
            msg2 = "workshop";
            intent4.putExtra("message", msg2);
            startActivity(intent4);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
