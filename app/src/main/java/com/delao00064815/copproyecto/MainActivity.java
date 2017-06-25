package com.delao00064815.copproyecto;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.delao00064815.copproyecto.about_us.AboutUs;
import com.delao00064815.copproyecto.directorio.Directorio;
import com.delao00064815.copproyecto.ofertaEmpleo.OfertaEmpleo;
import com.delao00064815.copproyecto.talleres.Talleres;

public class MainActivity extends AppCompatActivity {
    TextView txtEmpleados;
    TextView txtTalleres;
    TextView txtDirectorio;
    TextView txtAboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmpleados = (TextView) findViewById(R.id.txtEmpleados);
        txtTalleres = (TextView) findViewById(R.id.txtTalleres);
        txtDirectorio = (TextView) findViewById(R.id.txtDirectorio);
        txtAboutUs = (TextView) findViewById(R.id.txtAboutUs);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Light.ttf");
        txtEmpleados.setTypeface(typeface);
        txtTalleres.setTypeface(typeface);
        txtDirectorio.setTypeface(typeface);
        txtAboutUs.setTypeface(typeface);


    }
/*spinner*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
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
    /*spinner*/
    public void onClick(View view){
        int id = view.getId();
        switch (id) {
            case R.id.aboutUs:
                Intent intent = new Intent(this, AboutUs.class);
                startActivity(intent);
                break;
            case R.id.directorio:
                Intent intent2 = new Intent(this, Directorio.class);
                startActivity(intent2);
                break;
            case R.id.ofertas_empleo:
                Intent intent3 = new Intent(this, OfertaEmpleo.class);
                startActivity(intent3);
                break;
            case R.id.taller_img:
                Intent intent4 = new Intent(this, Talleres.class);
                startActivity(intent4);

        }

    }
}
