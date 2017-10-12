package com.delao00064815.copproyecto.directorio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

//import com.delao00064815.copproyecto.LoadData;
import com.delao00064815.copproyecto.LoadData;
import com.delao00064815.copproyecto.Login;
import com.delao00064815.copproyecto.MainActivity;
import com.delao00064815.copproyecto.R;
import com.delao00064815.copproyecto.talleres.Historial;

import java.util.concurrent.ExecutionException;

/**
 * Created by hmanr on 5/6/2017.
 */

public class Directorio extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DAdapter dAdapter;
    ListView rView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_dir);

        rView=(ListView) findViewById(R.id.mRecyclerDirectory);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
// Put this in OnCreate

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        try {
            new LoadData(this,dAdapter,rView,"empleado").execute().get();
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
