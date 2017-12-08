package com.delao00064815.copproyecto.NetConection;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.delao00064815.copproyecto.MainActivity;
import com.delao00064815.copproyecto.ofertaEmpleo.OAdapter;
import com.delao00064815.copproyecto.ofertaEmpleo.OfertaClass;

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
 * Created by Manrique_Matus on 023/23/7/2017.
 */

public class NetOfertas extends AsyncTask<Void, Void, String> {

    static final String TAG = "NetOfertas";
    private Context context;
    private ProgressDialog pDialog;
    String condition="";

    private String response = "";
    private String type;
    private int idoferta;
    private boolean flag=false;


    //Arreglo
    ArrayList<OfertaClass> offer=new ArrayList<>();

    //URLs
    String ip2="cop-uca-com.stackstaging.com";
    String url_ofertas_imagen="http://"+ip2+"/WebServer/imagenes/ofertas/";
    String url_offers="http://"+ip2+"/WebServer/ofertas_empleo.php";
    String url_views="http://"+ip2+"/WebServer/vistas.php?idOferta=";

    //Lista y Adaptador
    ListView tList;
    OAdapter oAdapter;

    //Constructor
    public NetOfertas(Context c, OAdapter oa, ListView rv,String condicion) {
        context=c;
        oAdapter=oa;
        tList=rv;
        condition=condicion;
    }
    public NetOfertas(Context c,int id){
        context=c;
        idoferta=id;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context) ;
        pDialog.setMessage("Loading data. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        switch(condition){
            case "offer":
            try {
                response = getOferta(url_offers);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            break;
            default:
                try {
                    getOferta2(url_views,idoferta);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
        }
        return null;
    }
    @Override
    protected void onPostExecute(String result){
        if (flag==false) {
            switch (condition) {
                case "offer":
                    try {
                        setOffer(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    pDialog.dismiss();
                    break;
                default:
                    pDialog.dismiss();
                    break;
            }
        }
        else{
            Intent intent=new Intent(context,MainActivity.class);
            context.startActivity(intent);
            Toast.makeText(context, "Se produjo un error de conexion., intente de nuevo mas tarde.", Toast.LENGTH_SHORT).show();
        }
    }


    public String getOferta(String url) throws MalformedURLException {
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
            else
                flag=true;
        }
        catch (Exception e){

        }
        Log.d(TAG, "getTaller:"+result.toString()+"");
        return result.toString();
    }

    public String getOferta2(String url,int id) throws MalformedURLException {
        URL uri = new URL(url+id);
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
                Log.d(TAG, "getOferta2: Funciona");
                InputStream in =new BufferedInputStream(httpCon.getInputStream());
                BufferedReader read = new BufferedReader(new InputStreamReader(in));
                while ((linea=read.readLine())!=null){
                    result.append(linea);
                }
            }
            else
                flag=true;
        }
        catch (Exception e){

        }
        Log.d(TAG, "getOferta:"+result.toString()+"");
        return result.toString();
    }
    public void setOffer(String jsonCad) throws JSONException {
        JSONArray jsonArr=new JSONArray(jsonCad);
        Log.d(TAG, "setOffer: "+jsonCad);
        for (int i=0;i<jsonArr.length();i++){
            offer.add(new OfertaClass(jsonArr.getJSONObject(i).getInt("idOferta"),
                    jsonArr.getJSONObject(i).getString("nombreTipoOferta"),
                    jsonArr.getJSONObject(i).getString("empresa"),
                    jsonArr.getJSONObject(i).getString("descripcionEmpleo"),
                    jsonArr.getJSONObject(i).getString("cargo"),
                    jsonArr.getJSONObject(i).getString("fechaLimite"),
                    url_ofertas_imagen+jsonArr.getJSONObject(i).getString("img"),
                    jsonArr.getJSONObject(i).getString("nomCarrera"),
                    url_ofertas_imagen+jsonArr.getJSONObject(i).getString("img_detail")));
            Log.d(TAG, "setOffer: "+offer.get(i).getImg()+"");
        }
        Log.d(TAG, "setOffer: "+offer.get(1).getNomTipoOferta()+"");
        Log.d(TAG, "setOffer: "+offer.get(1).getEmpresa()+"");
        Log.d(TAG, "setOffer: "+offer.get(1).getCargo()+"");
        Log.d(TAG, "setOffer: "+offer.get(1).getImg()+"");

        oAdapter=new OAdapter(context,offer);
        tList.setAdapter(oAdapter);
    }


}
