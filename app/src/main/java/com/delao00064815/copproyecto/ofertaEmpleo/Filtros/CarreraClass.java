package com.delao00064815.copproyecto.ofertaEmpleo.Filtros;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by César on 20/10/2017.
 */

public class CarreraClass/* implements Parcelable*/ {
    public String idCarrera, nomCarrera;

    public String getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(String idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNomCarrera() {
        return nomCarrera;
    }

    public void setNomCarrera(String nomCarrera) {
        this.nomCarrera = nomCarrera;
    }

    public CarreraClass(String idCarrera, String nomCarrera) {
        this.idCarrera = idCarrera;
        this.nomCarrera = nomCarrera;
    }

    public CarreraClass(String nomCarrera) {
        this.nomCarrera = nomCarrera;
    }
    /*public CarreraClass(){
        idCarrera = new String();
        nomCarrera= new String();
    }
    public CarreraClass(Parcel in){
        idCarrera = new String();
        nomCarrera= new String();
        readFromParcel(in);
    }
    public static final Parcelable.Creator<CarreraClass> CREATOR = new Parcelable.Creator<CarreraClass>() {
        public CarreraClass createFromParcel(Parcel in) {
            return new CarreraClass(in);
        }

        public CarreraClass[] newArray(int size) {

            return new CarreraClass[size];
        }

    };

    public void readFromParcel(Parcel in) {
        idCarrera = in.readString();
        nomCarrera = in.readString();

    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idCarrera);
        dest.writeString(nomCarrera);

    }*/
}



