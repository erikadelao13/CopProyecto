package com.delao00064815.copproyecto;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.delao00064815.copproyecto.SocialNetworks.Facebook;
import com.delao00064815.copproyecto.about_us.AboutUs;
import com.delao00064815.copproyecto.cloudMessaging.RegistrationService;
import com.delao00064815.copproyecto.directorio.Directorio;
import com.delao00064815.copproyecto.ofertaEmpleo.OfertaEmpleo;
import com.delao00064815.copproyecto.talleres.Historial;
import com.delao00064815.copproyecto.talleres.Talleres;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView txtEmpleados;
    TextView txtTalleres;
    TextView txtDirectorio;
    TextView txtAboutUs;
    SessionManager session;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        session = new SessionManager(getApplicationContext());


        txtEmpleados = (TextView) findViewById(R.id.txtEmpleados);
        txtTalleres = (TextView) findViewById(R.id.txtTalleres);
        txtDirectorio = (TextView) findViewById(R.id.txtDirectorio);
        txtAboutUs = (TextView) findViewById(R.id.txtAboutUs);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Light.ttf");
        txtEmpleados.setTypeface(typeface);
        txtTalleres.setTypeface(typeface);
        txtDirectorio.setTypeface(typeface);
        txtAboutUs.setTypeface(typeface);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        /*if(session.isLoggedIn()){
            navigationView.getMenu().findItem(R.id.item1).setVisible(false);
            navigationView.getMenu().findItem(R.id.item5).setVisible(true);
        } else {
            navigationView.getMenu().findItem(R.id.item1).setVisible(true);
            navigationView.getMenu().findItem(R.id.item5).setVisible(false);
        }*/
    }


    /*spinner*/
    public void onClick(View view) {
        int id = view.getId();
        String msg;
        switch (id) {
            case R.id.aboutUs:
                Intent intent = new Intent(this, AboutUs.class);
                startActivity(intent);
                break;
            case R.id.directorio:
                Intent intent2 = new Intent(this, Directorio.class);
                msg = "directory";
                intent2.putExtra("message", msg);
                startActivity(intent2);
                break;
            case R.id.ofertas_empleo:
                Intent intent3 = new Intent(this, OfertaEmpleo.class);
                msg = "jobs";
                intent3.putExtra("message", msg);
                startActivity(intent3);
                break;
            case R.id.taller_img:

                Intent intent4 = new Intent(this, Talleres.class);
                msg = "workshop";
                intent4.putExtra("message", msg);
                startActivity(intent4);

        }
    }


    public void onClickIcon(View view) {
        int id = view.getId();
        String msg;
        switch (id) {
            case R.id.in:
                Uri uri = Uri.parse("https://www.instagram.com/cop_uca/");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/cop_uca/")));
                }
                break;
            case R.id.fa:
                Facebook fb = new Facebook();
                Intent facebookIntent = fb.newFacebookIntent(this.getPackageManager(),"https://www.facebook.com/Centro-de-Orientaci%C3%B3n-Profesional-UCA-El-Salvador-1118956984838065/");
                startActivity(facebookIntent);
                break;
            case R.id.tw:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=cop_uca")));
                }catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/cop_uca")));
                }

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