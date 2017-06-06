package com.delao00064815.copproyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.delao00064815.copproyecto.about_us.AboutUs;
import com.delao00064815.copproyecto.directorio.Directorio;
import com.delao00064815.copproyecto.ofertaEmpleo.OfertaEmpleo;
import com.delao00064815.copproyecto.talleres.Talleres;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

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
            Toast.makeText(getApplicationContext(),"Aun no funca pero eh :v",Toast.LENGTH_LONG).show();
        }
        /*else if(){

        }*/
        return true;
    }
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
            case R.id.talleres:
                Intent intent4 = new Intent(this, Talleres.class);

        }

    }
}
