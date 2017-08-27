package com.delao00064815.copproyecto.talleres;

/**
 * Created by César on 26/08/2017.
 */

public class ClaUsuario {
    String idcarrera, idestudiante, nomestudiante, carnete, password,ycarrera, idnotificacion;

    public ClaUsuario(String idestudiante, String idcarrera, String nomestudiante, String carnete, String password, String ycarrera, String idnotificacion) {
        this.idestudiante = idestudiante;
        this.idcarrera = idcarrera;
        this.nomestudiante = nomestudiante;
        this.carnete = carnete;
        this.password = password;
        this.ycarrera = ycarrera;
        this.idnotificacion = idnotificacion;
    }

    public String getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(String idcarrera) {
        this.idcarrera = idcarrera;
    }

    public String getIdestudiante() {
        return idestudiante;
    }

    public void setIdestudiante(String idestudiante) {
        this.idestudiante = idestudiante;
    }

    public String getNomestudiante() {
        return nomestudiante;
    }

    public void setNomestudiante(String nomestudiante) {
        this.nomestudiante = nomestudiante;
    }

    public String getCarnete() {
        return carnete;
    }

    public void setCarnete(String carnete) {
        this.carnete = carnete;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getYcarrera() {
        return ycarrera;
    }

    public void setYcarrera(String ycarrera) {
        this.ycarrera = ycarrera;
    }

    public String getIdnotificacion() {
        return idnotificacion;
    }

    public void setIdnotificacion(String idnotificacion) {
        this.idnotificacion = idnotificacion;
    }
}
