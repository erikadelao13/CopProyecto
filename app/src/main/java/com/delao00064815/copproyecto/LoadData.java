package com.delao00064815.copproyecto;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Switch;

import com.delao00064815.copproyecto.ofertaEmpleo.MyHolder;
import com.delao00064815.copproyecto.ofertaEmpleo.OfertaClass;
import com.delao00064815.copproyecto.talleres.AdaptadorTalleres;
import com.delao00064815.copproyecto.talleres.ClaTalleres;

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

/**
 * Created by hmanr on 25/6/2017.
 */

public class LoadData extends AsyncTask<Void, Void, String> {

    static final String TAG = "LoadData";
    private Context context;
    private ProgressDialog pDialog;

    private String response = "";
    private String type;
    private String parameter;

    //Arreglos
    ArrayList<OfertaClass> offer=new ArrayList<>();
    ArrayList<ClaTalleres> ws=new ArrayList<>();

    //URLs
    String ip="http://copuca-com.stackstaging.com/";
    String url_talleres="http://"+ip+"/WebServer/imagenes/talleres/";
    String url_ofertas="http://"+ip+"/WebServer/imagenes/ofertas/";

    String url_workshop="http://"+ip+"/WebServer/talleres.php";
    String url_employers="http://"+ip+"/WebServer/empleados.php";
    String url_offers="http://"+ip+"/WebServer/ofertas.php";

    //Listas
    ListView tList;
    RecyclerView rView;
    //Adaptadores
    AdaptadorTalleres tAdapter;
    MyHolder mHolder;



    //Constructor para Talleres
    public LoadData(Context c, AdaptadorTalleres adapter, ListView list,String condition){
        context=c;
        tAdapter=adapter;
        tList=list;
        type=condition;
    }

    //Constructor para ofertas de empleo
    public LoadData(Context c,MyHolder mH,RecyclerView rv,String condition) {
        context=c;
        mHolder=mH;
        rView=rv;
        type=condition;
    }


    //Constructor vacio
    public LoadData(){}

//Proceso previo
    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        pDialog = new ProgressDialog(context) ;
        pDialog.setMessage("Loading data. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    //Acciones que se realizan en segundo plano
    @Override
    protected String doInBackground(Void... params) {
         switch(type) {
            case "taller":
                try {
                    getInfoWeb(url_workshop);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
             case "oferta":
                 try {
                     getInfoWeb(url_offers);
                 } catch (MalformedURLException e) {
                     e.printStackTrace();
                 }
                 break;
             case "empleados":
                 try {
                     getInfoWeb(url_employers);
                 } catch (MalformedURLException e) {
                     e.printStackTrace();
                 }
                 break;
         }
        return null;
    }

    //Aqui seteo la info en las vistas
    @Override
    protected void onPostExecute(String result) {
        switch (type){
            case "taller":
                try {
                    setWorkshop(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case "oferta":
                try {
                    setOffer(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case "empleado":
                try {
                    setEmployers(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

        }
        pDialog.dismiss();

    }

    public String getInfoWeb(String url) throws MalformedURLException {
        URL uri = new URL(url);
        String linea ="";
        StringBuilder result = null;
        int respuesta = 0;
        try {
            Log.d(TAG, "getInfoWeb: "+uri+"");
            HttpURLConnection httpCon = (HttpURLConnection)uri.openConnection();
            httpCon.setReadTimeout(20000);
            httpCon.setConnectTimeout(20000);
            httpCon.setDoInput(true);
            httpCon.setDoOutput(true);
            respuesta =httpCon.getResponseCode();
            result = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK){
                Log.d(TAG, "getInfoWeb: Funciona");
                InputStream in =new BufferedInputStream(httpCon.getInputStream());
                BufferedReader read = new BufferedReader(new InputStreamReader(in));
                while ((linea=read.readLine())!=null){
                    result.append(linea);
                }
            }
        }
        catch (Exception e){

        }
        Log.d(TAG, "enviarjuegosGET:"+result.toString()+"");
        return result.toString();
    }

    public void setWorkshop(String jsonCad) throws JSONException {
        JSONArray jsonArr=new JSONArray(jsonCad);
        for (int i=0;i<jsonArr.length();i++){
            ws.add(new ClaTalleres(jsonArr.getJSONObject(i).getInt("idTaller"),
                    jsonArr.getJSONObject(i).getString("nomTaller"),
                    jsonArr.getJSONObject(i).getString("fechaTaller"),
                    jsonArr.getJSONObject(i).getString("nomCategoria"),
                    url_talleres+jsonArr.getJSONObject(i).getString("imgTaller")));
        }
        //tAdapter=new AdaptadorTalleres(context,R.id.listView,ws);
        tList.setAdapter(tAdapter);

    }

    public void setOffer(String jsonCad) throws JSONException {
        JSONArray jsonArr=new JSONArray(jsonCad);
        for (int i=0;i<jsonArr.length();i++){
            offer.add(new OfertaClass(jsonArr.getJSONObject(i).getInt("idOferta"),
                    jsonArr.getJSONObject(i).getString("nomTipoOferta"),
                    jsonArr.getJSONObject(i).getString("empresa"),
                    jsonArr.getJSONObject(i).getString("cargo"),
                    url_ofertas+jsonArr.getJSONObject(i).getString("imgOferta"),
                    jsonArr.getJSONObject(i).getString("nomCarrera")));
        }
        //Faltan adaptadores
    }
    public void setEmployers(String jsoncad) throws JSONException {
        JSONArray jsonArr=new JSONArray(jsoncad);
        for (int i=0;i<jsonArr.length();i++){

        }
        //Faltan adaptadores
    }
}
