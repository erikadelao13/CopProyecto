package com.delao00064815.copproyecto.talleres;

/**
 * Created by David on 25-Jun-17.
 */

public class ClaTalleres {
    private int idTaller;
    private String nomTaller;
    private String fechaTaller;
    private String nomCategoria;
    private String imgTaller;

    public ClaTalleres(int idTaller, String nomTaller, String fechaTaller, String nomCategoria, String imgTaller) {
        this.idTaller = idTaller;
        this.nomTaller = nomTaller;
        this.fechaTaller = fechaTaller;
        this.nomCategoria = nomCategoria;
        this.imgTaller = imgTaller;

    }

    public int getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(int idTaller) {
        this.idTaller = idTaller;
    }

    public String getNomTaller() {
        return nomTaller;
    }

    public void setNomTaller(String nomTaller) {
        this.nomTaller = nomTaller;
    }

    public String getFechaTaller() {
        return fechaTaller;
    }

    public void setFechaTaller(String fechaTaller) {
        this.fechaTaller = fechaTaller;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }

    public String getImgTaller() {
        return imgTaller;
    }

    public void setImgTaller(String imgTaller) {
        this.imgTaller = imgTaller;
    }

}
