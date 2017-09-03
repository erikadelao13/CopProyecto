package com.delao00064815.copproyecto.talleres;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.delao00064815.copproyecto.LoadData;
import com.delao00064815.copproyecto.Login;
import com.delao00064815.copproyecto.R;
import com.delao00064815.copproyecto.SessionManager;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.delao00064815.copproyecto.SessionManager.KEY_CARNET;

/**
 * Created by hmanr on 5/6/2017.
 */

public class Historial extends AppCompatActivity {
    Button register;
    ListView listView;
    ArrayList<String> images;
    AdaptadorTalleres myAdapter;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        setContentView(R.layout.activity_drawer_talleres);
        //aqui deberia sacar la lista de la base de datos supongo
        images = new ArrayList<String>() {
        };
        listView = (ListView) findViewById(R.id.listView);

        try {
            new LoadData(this,myAdapter,listView,"taller",String.valueOf(session.getUserDetails().get(KEY_CARNET))/*String.valueOf(session.getUserDetails())"00025815"*/).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
