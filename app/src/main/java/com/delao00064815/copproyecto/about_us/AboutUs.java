package com.delao00064815.copproyecto.about_us;

import android.content.Intent;
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
import android.widget.Toast;

import com.delao00064815.copproyecto.Login;
import com.delao00064815.copproyecto.MainActivity;
import com.delao00064815.copproyecto.R;
import com.delao00064815.copproyecto.SessionManager;
import com.delao00064815.copproyecto.talleres.Historial;

/**
 * Created by hmanr on 5/6/2017.
 */

public class AboutUs extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SessionManager session;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_us);

        session = new SessionManager(getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#212438"));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

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


