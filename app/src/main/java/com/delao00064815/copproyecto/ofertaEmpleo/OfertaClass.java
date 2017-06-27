package com.delao00064815.copproyecto.ofertaEmpleo;

/**
 * Created by César on 25/06/2017.
 */

public class OfertaClass {
    int idOferta;
    String nomTipoOferta, empresa,remuneracion,descripcionEmpleo, cargo, fechaLimite,img,carrera;


    public OfertaClass(int idOferta, String nomTipoOferta, String empresa, String cargo, String img, String carrera) {
        this.idOferta = idOferta;
        this.nomTipoOferta = nomTipoOferta;
        this.empresa = empresa;
        this.cargo = cargo;
        this.img = img;
        this.carrera =carrera;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public String getnomTipoOferta(){ return nomTipoOferta;}

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public String getIdTipoOferta() {
        return nomTipoOferta;
    }

    public void setIdTipoOferta(String idTipoOferta) {
        this.nomTipoOferta = idTipoOferta;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getRemuneracion() {
        return remuneracion;
    }

    public void setRemuneracion(String remuneracion) {
        this.remuneracion = remuneracion;
    }

    public String getDescripcionEmpleo() {
        return descripcionEmpleo;
    }

    public void setDescripcionEmpleo(String descripcionEmpleo) {
        this.descripcionEmpleo = descripcionEmpleo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
