package com.delao00064815.copproyecto.ofertaEmpleo;

/**
 * Created by César on 25/06/2017.
 */

public class OfertaClass {
    int idOferta;
    String nomTipoOferta, empresa,remuneracion, descEmpleo, cargo, fechaLimite, img, carrera, img_detail;


    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public String getNomTipoOferta() {
        return nomTipoOferta;
    }

    public void setNomTipoOferta(String nomTipoOferta) {
        this.nomTipoOferta = nomTipoOferta;
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

    public String getDescEmpleo() {
        return descEmpleo;
    }

    public void setDescEmpleo(String descEmpleo) {
        this.descEmpleo = descEmpleo;
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

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getImg_detail(){ return img_detail; }

    public void setImg_detail(String img_detail){this.img_detail=img_detail;}

    public OfertaClass(int idOferta, String nomTipoOferta, String empresa, String descEmpleo, String cargo, String fechaLimite, String img, String carrera,String img_detail) {
        this.idOferta = idOferta;
        this.nomTipoOferta = nomTipoOferta;
        this.empresa = empresa;
        this.descEmpleo=descEmpleo;
        this.cargo = cargo;
        this.fechaLimite = fechaLimite;
        this.img = img;
        this.carrera =carrera;
        this.img_detail=img_detail;
    }

}
