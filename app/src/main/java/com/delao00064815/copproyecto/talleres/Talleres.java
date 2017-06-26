package com.delao00064815.copproyecto.talleres;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.delao00064815.copproyecto.Login;
import com.delao00064815.copproyecto.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hmanr on 5/6/2017.
 */

public class Talleres extends AppCompatActivity {
    Button register;
    ListView listView;
    List<String> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_talleres);
        //aqui deberia sacar la lista de la base de datos supongo
        images = new ArrayList<String>() {
        };
        images.add("official4");
        images.add("official4");
        images.add("official4");
        images.add("official4");
        images.add("official4");

        AdaptadorTalleres myAdapter = new AdaptadorTalleres(this, R.layout.activity_talleres, images);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(myAdapter);
        /*register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(Talleres.this);
                View mView = getLayoutInflater().inflate(R.layout.custom_dialog,null);
                Button aceptar = (Button) findViewById(R.id.aceptar);
                aceptar.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Talleres.this, "Has sido registrado correctamente.",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                builder.setView(mView);
                AlertDialog dialog = builder.create();
                dialog.show();
                builder.setTitle("Esta a punto de inscribirse a este taller");
                builder.setTitle("Estas seguro que deseas inscribirte?, si es asi, por favor oprime Aceptar, de lo contrario, oprima No.");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                } );
                builder.setNegativeButton("No",null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });*/


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
        if(res_id==R.id.login){
            Intent i = new Intent(this, Login.class);
            startActivity(i);
        }
        /*else if(){

        }*/
        return true;
    }
}
