package com.delao00064815.copproyecto.talleres;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.delao00064815.copproyecto.R;

import java.util.ArrayList;

/**
 * Created by rober on 26/6/2017.
 */

public class DialogRegistroTalleres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        Button register = (Button) findViewById(R.id.register);
        /*register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(DialogRegistroTalleres.this);
                View mView = getLayoutInflater().inflate(R.layout.custom_dialog,null);
                Button aceptar = (Button) findViewById(R.id.aceptar);
                aceptar.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DialogRegistroTalleres.this, "Has sido registrado correctamente.",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                builder.setView(mView);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });*/

        Button aceptar = (Button) findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(DialogRegistroTalleres.super.getApplicationContext(), "Has sido registrado correctamente.",Toast.LENGTH_LONG).show();
                //finish();
            }
        });
    }
}
