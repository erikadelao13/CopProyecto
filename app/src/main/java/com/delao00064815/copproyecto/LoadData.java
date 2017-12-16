package com.delao00064815.copproyecto;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.delao00064815.copproyecto.R;
import com.delao00064815.copproyecto.directorio.DAdapter;
import com.delao00064815.copproyecto.directorio.DirectorioClass;
import com.delao00064815.copproyecto.ofertaEmpleo.OAdapter;
import com.delao00064815.copproyecto.ofertaEmpleo.OfertaAdapter;
import com.delao00064815.copproyecto.ofertaEmpleo.OfertaClass;
import com.delao00064815.copproyecto.talleres.AdaptadorHistorial;
import com.delao00064815.copproyecto.talleres.AdaptadorTalleres;
import com.delao00064815.copproyecto.talleres.ClaTalleres;
import com.delao00064815.copproyecto.talleres.ClaUsuario;
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
import java.util.concurrent.ExecutionException;

/**
 * Created by hmanr on 25/6/2017.
 */

public class LoadData extends AsyncTask<Void, Void, String> {
    static final String TAG = "LoadData";
    private Context context;
    private ProgressDialog pDialog;
    private String response = "";
    private String type;
    private String filtro;
    private String user;
    private ListView listView;
    boolean flag=true;
    AdaptadorTalleres myAdapter;
    SessionManager session;

    //Arreglos
    ArrayList<OfertaClass> offer=new ArrayList<>();
    ArrayList<ClaTalleres> ws=new ArrayList<>();
    ArrayList<DirectorioClass> direct=new ArrayList<>();
    ArrayList<ClaUsuario> arregloU = new ArrayList<>();

    //URLs
    String ip2="cop-uca-com.stackstaging.com";
    String ip="10.45.7.31";
    String url_talleres="http://"+ip2+"/WebServer/imagenes/talleres/";
    String url_ofertas="http://"+ip2+"/WebServer/imagenes/ofertas/";
    String url_Usuario="";
    String url_workshop="http://"+ip2+"/WebServer/talleres.php";
    String url_employers="http://"+ip2+"/WebServer/empleados.php";
    String url_offers="http://"+ip2+"/WebServer/ofertas_empleo.php";
    String url_workshopSignUp="http://"+ip2+"/WebServer/registroTalleres.php";

    //Listas
    ListView tList;
    RecyclerView rView1;
    RecyclerView rList;

    //Adaptadores
    AdaptadorTalleres tAdapter;
    AdaptadorHistorial hAdapter;
    DAdapter dAdapter;
    OfertaAdapter oAdapter;



    //Constructor para Talleres
    public LoadData(Context c, AdaptadorTalleres adapter, ListView list,String condition,String carnetuser){
        context=c;
        tAdapter=adapter;
        tList=list;
        type=condition;
        user=carnetuser;
    }
    //Constructor para Historial
    public LoadData(Context c, AdaptadorHistorial adapter, ListView list,String condition,String carnetuser){
        context=c;
        hAdapter=adapter;
        tList=list;
        type=condition;
        user=carnetuser;
    }

    //Constructor para ofertas de empleo
    public LoadData(Context c, OfertaAdapter oa, RecyclerView rv, String condition) {
        context=c;
        oAdapter=oa;
        rList=rv;
        type=condition;
    }
    public LoadData(Context c,OfertaAdapter oa, RecyclerView rv,String condition, String filtros){
        context=c;
        oAdapter=oa;
        rList=rv;
        type=condition;
        filtro=filtros;
    }

    //Constructor para directorio
    public LoadData(Context c,DAdapter adapter, ListView rv,String condition){
        context=c;
        dAdapter=adapter;
        tList=rv;
        type=condition;
    }


    //Constructor para agregarTaller
    public LoadData(Context c,String condition){
        context=c;
        type=condition;
        session = new SessionManager(context);
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
        //pDialog.show();
        pDialog.setCancelable(false);

    }

    //Acciones que se realizan en segundo plano
    @Override
    protected String doInBackground(Void... params) {
         switch(type) {
            /*case "taller":
                try {
                    url_Usuario="http://"+ip2+"/WebServer/getuser.php?carnetE="+user;
                    response=getInfoWeb(url_Usuario);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;*/
             /*case "historial":
                 try {
                     url_Usuario="http://"+ip2+"/WebServer/getuser.php?carnetE="+user;
                     response=getInfoWeb(url_Usuario);
                 } catch (MalformedURLException e) {
                     e.printStackTrace();
                 }
                 break;*/
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
             case "ofertaCarrera":
                 try {
                     response=getInfoWeb(filtro);
                 } catch (MalformedURLException e) {
                     e.printStackTrace();
                 }
                 break;
             case "ofertaTipo":
                 try {
                     response=getInfoWeb(filtro);
                 } catch (MalformedURLException e) {
                     e.printStackTrace();
                 }
                 break;
             case "tallerUser":
                 try {
                     url_Usuario="http://"+ip2+"/WebServer/talleres.php?idUsuario="+user;
                     response=getInfoWeb(url_Usuario);
                 } catch (MalformedURLException e) {
                     e.printStackTrace();
                 }
                 break;
             case "historialUser":
                 try {
                     url_Usuario="http://"+ip2+"/WebServer/historialT.php?idUsuario="+user;
                     response=getInfoWeb(url_Usuario);
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
        if(response=="") {
            Intent intent=new Intent(context,MainActivity.class);
            context.startActivity(intent);
            Toast.makeText(context, "Se produjo un error de conexion., intente de nuevo mas tarde.", Toast.LENGTH_SHORT).show();
            ((Activity)context).finish();
        }
        else {
            switch (type) {
            /*case "taller":
                try {
                    setUser(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;*/
                case "tallerUser":
                    try {
                        setWorkshop(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
           /* case "historial":
                try {
                    setUser(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;*/
                case "historialUser":
                    try {
                        setWorkHist(response);
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
                case "ofertaCarrera":
                    try {
                        setOffer(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case "ofertaTipo":
                    try {
                        setOffer(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

        pDialog.dismiss();

    }

    public String getInfoWeb(String url) throws MalformedURLException {
        URL uri = new URL(url);
        String linea ="";
        StringBuilder result = null;
        int respuesta = 0;
        String exit="";
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
                exit=result.toString();
            }
            else{
                flag=false;

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
      // Log.d(TAG, "getInfoWeb:"+result.toString()+"");
        return exit;
    }

    public  void signUp(String idTaller) throws UnsupportedEncodingException {
        // Get user defined values
//        Log.d("signinUp", carnetE);
        /*
        if(!session.isLoggedIn()){
            return;
        }
        */
        BufferedReader reader=null;

        // Send data
        try
        {
            // Defined URL  where to send data
            URL uri = new URL(url_workshopSignUp+"?carnetE="+session.getUserDetails().get(SessionManager.KEY_CARNET)+"&idTaller="+idTaller);

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
                    /*jsonArr.getJSONObject(i).getString("nomCategoria"),*/
                    url_talleres+jsonArr.getJSONObject(i).getString("imgTaller")));
        }
        /*Log.d(TAG, "setWorkshop: "+ws.get(0).getNomTaller()+"");
        Log.d(TAG, "setWorkshop: "+ws.get(0).getIdTaller()+"");
        Log.d(TAG, "setWorkshop: "+ws.get(0).getFechaTaller()+"");
        Log.d(TAG, "setWorkshop: "+ws.get(0).getNomCategoria()+"");
        Log.d(TAG, "setWorkshop: "+ws.get(0).getImgTaller()+"");*/

        tAdapter=new AdaptadorTalleres(context,R.layout.activity_talleres,ws);
        tList.setAdapter(tAdapter);

    }
    public void setWorkHist(String jsonCad) throws JSONException {

            JSONArray jsonArr = new JSONArray(jsonCad);

            for (int i = 0; i < jsonArr.length(); i++) {
                ws.add(new ClaTalleres(jsonArr.getJSONObject(i).getInt("idTaller"),
                        jsonArr.getJSONObject(i).getString("nomTaller"),
                        jsonArr.getJSONObject(i).getString("aulaTaller"),
                        jsonArr.getJSONObject(i).getString("fechaTaller"),
                       /* jsonArr.getJSONObject(i).getString("nomCategoria"),*/
                        url_talleres + jsonArr.getJSONObject(i).getString("imgTaller")));
            }
        /*Log.d(TAG, "setWorkshop: "+ws.get(0).getNomTaller()+"");
        Log.d(TAG, "setWorkshop: "+ws.get(0).getIdTaller()+"");
        Log.d(TAG, "setWorkshop: "+ws.get(0).getFechaTaller()+"");
        Log.d(TAG, "setWorkshop: "+ws.get(0).getNomCategoria()+"");
        Log.d(TAG, "setWorkshop: "+ws.get(0).getImgTaller()+"");*/



            hAdapter = new AdaptadorHistorial(context, R.layout.activity_historial, ws);
            tList.setAdapter(hAdapter);
            }
    
    public void setUser(String jsonCad) throws JSONException {

        JSONArray jsonArr=new JSONArray(jsonCad);
        for (int i=0;i<jsonArr.length();i++) {
            arregloU.add(new ClaUsuario(
                    jsonArr.getJSONObject(i).getString("idUsuario"),
                    jsonArr.getJSONObject(i).getString("idCarrera"),
                    jsonArr.getJSONObject(i).getString("nomEstudiante"),
                    jsonArr.getJSONObject(i).getString("carnetE"),
                    jsonArr.getJSONObject(i).getString("password"),
                    jsonArr.getJSONObject(i).getString("yCarrera")));

        }
       /* url_Usuario = "http://" + ip2 + "/WebServer/talleres.php?idUsuario=" + arregloU.get(0).getIdestudiante();
        try {
            response=getInfoWeb(url_Usuario);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/
       /*if(type=="taller"){
           try {
               new LoadData(context,tAdapter,tList,"tallerUser",arregloU.get(0).getIdestudiante()).execute().get();
           } catch (InterruptedException e) {
               e.printStackTrace();
           } catch (ExecutionException e) {
               e.printStackTrace();
           }

       }*/
      /* else{
           try {
               new LoadData(context,tAdapter,tList,"historialUser",arregloU.get(0).getIdestudiante()).execute().get();
           } catch (InterruptedException e) {
               e.printStackTrace();
           } catch (ExecutionException e) {
               e.printStackTrace();
           }

       }*/

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
                    url_ofertas+jsonArr.getJSONObject(i).getString("img"),
                    jsonArr.getJSONObject(i).getString("nomCarrera"),
                    url_ofertas+jsonArr.getJSONObject(i).getString("img_detail")));
            //Log.d(TAG, "setOffer: "+offer.get(i).getImg()+"");
        }
       /* Log.d(TAG, "setOffer: "+offer.get(1).getNomTipoOferta()+"");
        Log.d(TAG, "setOffer: "+offer.get(1).getEmpresa()+"");
        Log.d(TAG, "setOffer: "+offer.get(1).getCargo()+"");
        Log.d(TAG, "setOffer: "+offer.get(1).getImg()+"");*/

        oAdapter=new OfertaAdapter(context,offer);
        rList.setLayoutManager(new LinearLayoutManager(context));
        rList.setAdapter(oAdapter);

    }
    public void setEmployers(String jsoncad) throws JSONException {
        JSONArray jsonArr=new JSONArray(jsoncad);
        for (int i=0;i<jsonArr.length();i++){
            direct.add(new DirectorioClass(jsonArr.getJSONObject(i).getInt("idEmpleado"),
                    jsonArr.getJSONObject(i).getString("nombreEmpleado"),
                    jsonArr.getJSONObject(i).getString("correoEmpleado"),
                    jsonArr.getJSONObject(i).getString("cargoEmpleado")));
        }
        //Log.d(TAG, "setEmployers: "+direct.get(0).getNombreEmpleado()+"");
        dAdapter=new DAdapter(context, R.layout.directory_content,direct);
        tList.setAdapter(dAdapter);
    }
}
