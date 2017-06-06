package com.delao00064815.copproyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.delao00064815.copproyecto.designs.TrabajosPasan;


public class MainActivity extends AppCompatActivity {
    ImageView ofertas;
    ImageView talleres;
    ImageView aboutUs;
    ImageView directorio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        directorio = (ImageView) findViewById(R.id.directorio);
        aboutUs = (ImageView) findViewById(R.id.aboutUs);
        talleres = (ImageView) findViewById(R.id.talleres);
        ofertas = (ImageView) findViewById(R.id.ofertas_empleo);
    }

    public void ofertas_empleo(View v){
        Intent ofertas = new Intent(this,TrabajosPasan.class);
        startActivity(ofertas);
    }

    public void talleres(View v){

    }

    public void aboutUs(View v){

    }

    public void directorio(View v){

    }
/* Para el spinner*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int res_id = item.getItemId();
        if(res_id==R.id.register){
            Toast.makeText(getApplicationContext(),"Usted ha decidido registrarse!",Toast.LENGTH_LONG).show();
        }
        /*else if(){

        }*/
        return true;
    }
/******************/


}
