package com.delao00064815.copproyecto.ofertaEmpleo.Filtros;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.delao00064815.copproyecto.LoadData;
import com.delao00064815.copproyecto.MainActivity;
import com.delao00064815.copproyecto.ofertaEmpleo.OfertaAdapter;
import com.delao00064815.copproyecto.ofertaEmpleo.OfertaEmpleo;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutionException;

/**
 * Created by César on 25/07/2017.
 */

public class FiltroCarrera extends android.support.v4.app.DialogFragment {
    OfertaAdapter OferAdapter;
    String opciones;
    private ProgressDialog pDialog;
    private String response = "";
    List<String> listitems = new ArrayList<String>();
    ArrayList<CarreraClass> carreraa=new ArrayList<>();
    AlertDialog.Builder bund;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        bindDialog();
        return builder.create();
    }


    public void bindDialog()
    {

        new MyAsyncTask(getActivity()).execute();
    }

    class MyAsyncTask extends AsyncTask<Void, Void, String>
    {

        Activity mContex;
        public  MyAsyncTask(Activity contex)
        {
            mContex=contex;
        }
        //@Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity()) ;
            pDialog.setMessage("Loading data. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
           // pDialog.show();

        }

        protected String doInBackground(Void... params)
        {
            try {
               String url_Usuario="http://copuca-com.stackstaging.com/WebServer/getCarrera.php";
               response=getInfoWeb(url_Usuario);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }
        public String getInfoWeb(String url) throws MalformedURLException {
            URL uri = new URL(url);
            String linea ="";
            StringBuilder result = null;
            int respuesta = 0;
            try {
                // Log.d(TAG, "getInfoWeb: "+uri+"");
                HttpURLConnection httpCon = (HttpURLConnection)uri.openConnection();
                httpCon.setReadTimeout(20000);
                httpCon.setConnectTimeout(20000);
                httpCon.setDoInput(true);
                httpCon.setDoOutput(true);
                respuesta =httpCon.getResponseCode();
                result = new StringBuilder();
                if (respuesta == HttpURLConnection.HTTP_OK){
                    //Log.d(TAG, "getInfoWeb: Funciona");
                    InputStream in =new BufferedInputStream(httpCon.getInputStream());
                    BufferedReader read = new BufferedReader(new InputStreamReader(in));
                    while ((linea=read.readLine())!=null){
                        result.append(linea);
                    }
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }
            // Log.d(TAG, "getInfoWeb:"+result.toString()+"");
            return result.toString();
        }
        @Override
        protected void onPostExecute(String result)
            {

                try {
                    prueba(setCarrera(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        public CharSequence[] setCarrera(String jsoncad) throws JSONException {
            JSONArray jsonArr=new JSONArray(jsoncad);
            for (int i=0;i<jsonArr.length();i++){


                   //carreraa.add(new CarreraClass(/*jsonArr.getJSONObject(i).getString("idCarrera"),*/ jsonArr.getJSONObject(i).getString("nomCarrera")));
                    //System.out.println("PROBANDO ESTA WEA LOAD2"+jsonArr.getJSONObject(i).getString("nomCarrera"));
                    listitems.add(jsonArr.getJSONObject(i).getString("nomCarrera"));
                   // System.out.println("adentro"+listitems.get(i));
            }
          //  System.out.println("PROBA"+listitems.get(0));
             CharSequence[] charSequenceItems = listitems.toArray(new CharSequence[listitems.size()]);;

            return charSequenceItems;

        }

            protected void prueba(CharSequence[] a){

                /*Bundle extras = getActivity().getIntent().getExtras();*/
                final CharSequence[] carrera =a; /*extras.getParcelableArrayList("carrera");*/


               bund = new AlertDialog.Builder(mContex);
               // System.out.println("PROBANDO ESTA WEA LOAD2"+carrera[0]);
                bund.setTitle("Selecciona la Carrera para la que buscas oferta ").setSingleChoiceItems(carrera, -1, new DialogInterface.OnClickListener()  {
                    @Override

                    public void onClick(DialogInterface dialog, int args1) {
                        opciones = (String) carrera[args1];
                        // System.out.println("PROBANDO ESTA WEA DESPUÉS DEL CLICK"+carrera.get(args1).idCarrera);

                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(),"Filtro por carrera activado "+ opciones, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), OfertaEmpleo.class);
                        intent.putExtra("message",opciones);
                        startActivity(intent);
                    }
                });
                bund.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                    } });
              AlertDialog alert=  bund.create();
            alert.show();
            }
        }

}
