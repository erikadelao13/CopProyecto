package com.delao00064815.copproyecto.directorio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

//import com.delao00064815.copproyecto.LoadData;
import com.delao00064815.copproyecto.LoadData;
import com.delao00064815.copproyecto.Login;
import com.delao00064815.copproyecto.R;

import java.util.concurrent.ExecutionException;

/**
 * Created by hmanr on 5/6/2017.
 */

public class Directorio extends AppCompatActivity {
    DAdapter dAdapter;
    ListView rView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directory);

        rView=(ListView) findViewById(R.id.mRecyclerDirectory);

        try {
            new LoadData(this,dAdapter,rView,"empleado").execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        if(pref.contains("carnetE")){
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.menu_session,menu);
            MenuItem menuItem = (MenuItem) findViewById(R.id.sesion);
            menuItem.setTitle("sesion iniciada como: "+pref.getString("carnetE",null));
        } else {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.menu, menu);
        }
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
