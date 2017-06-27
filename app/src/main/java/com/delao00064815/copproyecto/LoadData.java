package com.delao00064815.copproyecto;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Switch;

import com.delao00064815.copproyecto.directorio.DirectAdapter;
import com.delao00064815.copproyecto.directorio.DirectorioClass;
import com.delao00064815.copproyecto.ofertaEmpleo.MyHolder;
import com.delao00064815.copproyecto.ofertaEmpleo.OfertaAdapter;
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
    ArrayList<DirectorioClass> direct=new ArrayList<>();

    //URLs
    //String ip="copuca-com.stackstaging.com/";
    String ip="10.45.7.31";
    String url_talleres="http://"+ip+"/WebServer/imagenes/talleres/";
    String url_ofertas="http://"+ip+"/WebServer/imagenes/ofertas/";

    String url_workshop="http://"+ip+"/COP/talleres.php";
    String url_employers="http://"+ip+"/WebServer/empleados.php";
    String url_offers="http://"+ip+"/COP/ofertas_empleo.php";

    //Listas
    ListView tList;
    RecyclerView rView1;
    RecyclerView rView2;

    //Adaptadores
    AdaptadorTalleres tAdapter;
    DirectAdapter dAdapter;
    OfertaAdapter oAdapter;



    //Constructor para Talleres
    public LoadData(Context c, AdaptadorTalleres adapter, ListView list,String condition){
        context=c;
        tAdapter=adapter;
        tList=list;
        type=condition;
    }

    //Constructor para ofertas de empleo
    public LoadData(Context c,OfertaAdapter oa,RecyclerView rv,String condition) {
        context=c;
        oAdapter=oa;
        rView1=rv;
        type=condition;
    }

    //Constructor para directorio
    public LoadData(Context c,DirectAdapter da,RecyclerView rv,String condition){
        context=c;
        dAdapter=da;
        rView2=rv;
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
                    response=getInfoWeb(url_workshop);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
             case "oferta":
                 try {
                     response=getInfoWeb(url_offers);
                 } catch (MalformedURLException e) {
                     e.printStackTrace();
                 }
                 break;
             case "empleado":
                 try {
                     response=getInfoWeb(url_employers);
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
        Log.d(TAG, "getInfoWeb:"+result.toString()+"");
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
        Log.d(TAG, "setWorkshop: "+ws.get(0).getNomTaller()+"");
        Log.d(TAG, "setWorkshop: "+ws.get(0).getIdTaller()+"");
        Log.d(TAG, "setWorkshop: "+ws.get(0).getFechaTaller()+"");
        Log.d(TAG, "setWorkshop: "+ws.get(0).getNomCategoria()+"");
        Log.d(TAG, "setWorkshop: "+ws.get(0).getImgTaller()+"");

        /*tAdapter=new AdaptadorTalleres(context,ws);
        tList.setAdapter(tAdapter);*/

    }

    public void setOffer(String jsonCad) throws JSONException {
        JSONArray jsonArr=new JSONArray(jsonCad);
        for (int i=0;i<jsonArr.length();i++){
            offer.add(new OfertaClass(jsonArr.getJSONObject(i).getInt("idOferta"),
                    jsonArr.getJSONObject(i).getString("nombreTipoOferta"),
                    jsonArr.getJSONObject(i).getString("empresa"),
                    jsonArr.getJSONObject(i).getString("cargo"),
                    url_ofertas+jsonArr.getJSONObject(i).getString("img"),
                    jsonArr.getJSONObject(i).getString("nomCarrera")));
        }
        Log.d(TAG, "setOffer: "+offer.get(1).getIdTipoOferta()+"");
        Log.d(TAG, "setOffer: "+offer.get(1).getnomTipoOferta()+"");
        Log.d(TAG, "setOffer: "+offer.get(1).getEmpresa()+"");
        Log.d(TAG, "setOffer: "+offer.get(1).getCargo()+"");
        Log.d(TAG, "setOffer: "+offer.get(1).getImg()+"");
        String gg="";

        oAdapter=new OfertaAdapter(context,offer);
        rView1.setAdapter(oAdapter);
    }
    public void setEmployers(String jsoncad) throws JSONException {
        JSONArray jsonArr=new JSONArray(jsoncad);
        for (int i=0;i<jsonArr.length();i++){
            direct.add(new DirectorioClass(jsonArr.getJSONObject(i).getInt("idEmpleado"),
                    jsonArr.getJSONObject(i).getString("nomEmpleado"),
                    jsonArr.getJSONObject(i).getString("correoEmpleado"),
                    jsonArr.getJSONObject(i).getString("cargo")));
        }
        dAdapter=new DirectAdapter(context,direct);
        rView2.setAdapter(dAdapter);
    }
}
