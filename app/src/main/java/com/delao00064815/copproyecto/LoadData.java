package com.delao00064815.copproyecto;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.delao00064815.copproyecto.directorio.DAdapter;
import com.delao00064815.copproyecto.directorio.DirectorioClass;
import com.delao00064815.copproyecto.ofertaEmpleo.OAdapter;
import com.delao00064815.copproyecto.ofertaEmpleo.OfertaClass;
import com.delao00064815.copproyecto.talleres.AdaptadorTalleres;
import com.delao00064815.copproyecto.talleres.ClaTalleres;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
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

    //Arreglos
    ArrayList<OfertaClass> offer=new ArrayList<>();
    ArrayList<ClaTalleres> ws=new ArrayList<>();
    ArrayList<DirectorioClass> direct=new ArrayList<>();

    //URLs
    String ip2="copuca-com.stackstaging.com";
    String ip="10.45.7.31";
    String url_talleres="http://"+ip2+"/WebServer/imagenes/talleres/";
    String url_ofertas="http://"+ip2+"/WebServer/imagenes/ofertas/";

    String url_workshop="http://"+ip2+"/WebServer/talleres.php";
    String url_employers="http://"+ip2+"/WebServer/empleados.php";
    String url_offers="http://"+ip2+"/WebServer/ofertas_empleo.php";
    String url_workshopSignUp="http://"+ip2+"/WebServer/registroTalleres.php";

    //Listas
    ListView tList;
    RecyclerView rView1;
    RecyclerView rView2;

    //Adaptadores
    AdaptadorTalleres tAdapter;
    DAdapter dAdapter;
    OAdapter oAdapter;



    //Constructor para Talleres
    public LoadData(Context c, AdaptadorTalleres adapter, ListView list,String condition){
        context=c;
        tAdapter=adapter;
        tList=list;
        type=condition;
    }

    //Constructor para ofertas de empleo
    public LoadData(Context c, OAdapter oa, ListView rv, String condition) {
        context=c;
        oAdapter=oa;
        tList=rv;
        type=condition;
    }

    //Constructor para directorio
    public LoadData(Context c,DAdapter adapter,ListView rv,String condition){
        context=c;
        dAdapter=adapter;
        tList=rv;
        type=condition;
    }

    //Constructor para agregarTaller
    public LoadData(Context c,String condition){
        context=c;
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
        //pDialog.show();

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
             default:
                 try {
                     signUp(type);
                 } catch (UnsupportedEncodingException e) {
                     e.printStackTrace();
                 }
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

    public  void signUp(String idTaller) throws UnsupportedEncodingException {
        // Get user defined values

        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("MyPref", 0);

        String carnetE = pref.getString("carnetE", null);
        Log.d("signinUp", carnetE);
        if(carnetE.equals(null)){
            return;
        }
        BufferedReader reader=null;

        // Send data
        try
        {
            // Defined URL  where to send data
            URL uri = new URL(url_workshopSignUp+"?carnetE="+carnetE+"&idTaller="+idTaller);

            HttpURLConnection httpCon = (HttpURLConnection)uri.openConnection();
            httpCon.setReadTimeout(20000);
            httpCon.setConnectTimeout(20000);
            httpCon.setDoInput(true);
            httpCon.setDoOutput(true);
            int respuesta =httpCon.getResponseCode();

            reader = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            Log.d("response", sb.toString());
        }
        catch(Exception ex)
        {

        }
        finally
        {
            try
            {
                reader.close();
            }
            catch(Exception ex) {}
        }

    }


    public void setWorkshop(String jsonCad) throws JSONException {
        JSONArray jsonArr=new JSONArray(jsonCad);
        for (int i=0;i<jsonArr.length();i++){
            ws.add(new ClaTalleres(jsonArr.getJSONObject(i).getInt("idTaller"),
                    jsonArr.getJSONObject(i).getString("nomTaller"),
                    jsonArr.getJSONObject(i).getString("aulaTaller"),
                    jsonArr.getJSONObject(i).getString("fechaTaller"),
                    jsonArr.getJSONObject(i).getString("nomCategoria"),
                    url_talleres+jsonArr.getJSONObject(i).getString("imgTaller")));
        }
        Log.d(TAG, "setWorkshop: "+ws.get(0).getNomTaller()+"");
        Log.d(TAG, "setWorkshop: "+ws.get(0).getIdTaller()+"");
        Log.d(TAG, "setWorkshop: "+ws.get(0).getFechaTaller()+"");
        Log.d(TAG, "setWorkshop: "+ws.get(0).getNomCategoria()+"");
        Log.d(TAG, "setWorkshop: "+ws.get(0).getImgTaller()+"");

        tAdapter=new AdaptadorTalleres(context,R.layout.activity_talleres,ws);
        tList.setAdapter(tAdapter);

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
                    url_ofertas+jsonArr.getJSONObject(i).getString("carrera"),
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
    public void setEmployers(String jsoncad) throws JSONException {
        JSONArray jsonArr=new JSONArray(jsoncad);
        for (int i=0;i<jsonArr.length();i++){
            direct.add(new DirectorioClass(jsonArr.getJSONObject(i).getInt("idEmpleado"),
                    jsonArr.getJSONObject(i).getString("nombreEmpleado"),
                    jsonArr.getJSONObject(i).getString("correoEmpleado"),
                    jsonArr.getJSONObject(i).getString("cargoEmpleado")));
        }
        Log.d(TAG, "setEmployers: "+direct.get(0).getNombreEmpleado()+"");
        dAdapter=new DAdapter(context,direct);
        tList.setAdapter(dAdapter);
    }
}
