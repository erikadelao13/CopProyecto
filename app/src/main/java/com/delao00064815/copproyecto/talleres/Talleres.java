package com.delao00064815.copproyecto.talleres;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.delao00064815.copproyecto.R;

/**
 * Created by hmanr on 5/6/2017.
 */

public class Talleres extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talleres);
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
}
