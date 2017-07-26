package com.delao00064815.copproyecto.NetConection;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

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

    private String response = "";
    private String type;


    //Arreglo
    ArrayList<OfertaClass> offer=new ArrayList<>();

    //URLs
    String ip2="copuca-com.stackstaging.com";
    String url_ofertas_imagen="http://"+ip2+"/WebServer/imagenes/ofertas/";
    String url_offers="http://"+ip2+"/WebServer/ofertas_empleo.php";

    //Lista y Adaptador
    ListView tList;
    OAdapter oAdapter;

    //Constructor
    public NetOfertas(Context c, OAdapter oa, ListView rv, String condition) {
        context=c;
        oAdapter=oa;
        tList=rv;
        type=condition;
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
        try {
            response=getOferta(url_offers);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    protected void onPostExecute(String result){
        try {
            setOffer(response);
        } catch (JSONException e) {
            e.printStackTrace();
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
        }
        catch (Exception e){

        }
        Log.d(TAG, "getTaller:"+result.toString()+"");
        return result.toString();
    }
    public void setOffer(String jsonCad) throws JSONException {
        JSONArray jsonArr=new JSONArray(jsonCad);
        Log.d(TAG, "setOffer: "+jsonCad);
        for (int i=0;i<jsonArr.length();i++){
            offer.add(new OfertaClass(jsonArr.getJSONObject(i).getInt("idOferta"),
                    jsonArr.getJSONObject(i).getString("nomTipoOferta"),
                    jsonArr.getJSONObject(i).getString("empresa"),
                    jsonArr.getJSONObject(i).getString("remuneracion"),
                    jsonArr.getJSONObject(i).getString("descEmpleo"),
                    jsonArr.getJSONObject(i).getString("cargo"),
                    jsonArr.getJSONObject(i).getString("fechaLimite"),
                    jsonArr.getJSONObject(i).getString("img"),
                    url_ofertas_imagen+jsonArr.getJSONObject(i).getString("carrera"),
                    jsonArr.getJSONObject(i).getInt("vistas")));
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