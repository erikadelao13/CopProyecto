package com.delao00064815.copproyecto.talleres;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    ListView listView;
    List<String> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_talleres);
        //aqui deberia sacar la lista de la base de datos supongo
        images = new ArrayList<String>() {
        };
        AdaptadorTalleres myAdapter = new AdaptadorTalleres(this, R.layout.activity_talleres, images);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(myAdapter);
    }
    public int getDrawableId(Context context, String name){
        return context.getResources().getIdentifier(name,"drawable", context.getPackageName());
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
