package com.delao00064815.copproyecto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by rober on 4/6/2017.
 */

public class Registro extends AppCompatActivity{
    private EditText etName,etCarnet, etYear,etPass;
    private  Spinner etCarrera;
    private Button btnSubmit;
    private ProgressDialog pDialog;
    private JSONObject json;
    private int success=0;
    private HTTPURLConnection service;
    private String strname ="", strpass ="",strcarnet="", stryear="";
    //Initialize webservice URL
    private String path = "http://192.168.1.7/WebService/registrar/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName= (EditText) findViewById(R.id.user);
        etPass= (EditText) findViewById(R.id.pass1);
        etCarrera= (Spinner) findViewById(R.id.carrera);
        etCarnet = (EditText) findViewById(R.id.carnet);
        etYear = (EditText) findViewById(R.id.year);

        btnSubmit= (Button) findViewById(R.id.btnSubmit);

        //Initialize HTTPURLConnection class object
        service=new HTTPURLConnection();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etName.getText().toString().equals("") && !etPass.getText().toString().equals("") && !etCarnet.getText().toString().equals("") && !etYear.getText().toString().equals("")) {
                    strname = etName.getText().toString();
                    strpass = etPass.getText().toString();
                    strcarnet = etCarnet.getText().toString();
                    stryear = etYear.getText().toString();
                    //Call WebService
                    new PostDataTOServer().execute();
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor llena todos los campos", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private class PostDataTOServer extends AsyncTask<Void, Void, Void> {

        String response = "";
        //Create hashmap Object to send parameters to web service
        HashMap<String, String> postDataParams;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(Registro.this);
            pDialog.setMessage("Espera por favor...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            postDataParams=new HashMap<String, String>();
            postDataParams.put("nomEstudiante", strname);
            postDataParams.put("password", strpass);
            postDataParams.put("carnetE", strcarnet);
            postDataParams.put("yCarrera", stryear);
            //Call ServerData() method to call webservice and store result in response
            response= service.ServerData(path,postDataParams);
            try {
                json = new JSONObject(response);
                //Get Values from JSONobject
                System.out.println("success=" + json.get("success"));
                success = json.getInt("success");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            if(success==1) {
                Toast.makeText(getApplicationContext(), "¡Has sido registrado exitosamente!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Registro.this,Login.class);
                startActivity(intent);
                Registro.this.finish();
            }
        }
    }
}
