package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Vehicle {
    private long id;
    private String constructeur;
    //private String modele;
    private int nb_places;
    public Vehicle(){
    }

    public Vehicle(long id, String cst, int nbP){
        this.id=id;
        constructeur=cst;
        //modele=md;
        nb_places=nbP;
    }
    public Vehicle( String cst, int nbP){

        constructeur=cst;
        //modele=md;
        nb_places=nbP;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConstructeur() {
        return constructeur;
    }

    public void setConstructeur(String constructeur) {
        this.constructeur = constructeur;
    }

    //public String getModele() {
        //return modele;
    //}

   // public void setModele(String modele) {
       // this.modele = modele;
    //}

    public int getNb_places() {
        return nb_places;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id == vehicle.id && nb_places == vehicle.nb_places && Objects.equals(constructeur, vehicle.constructeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, constructeur,  nb_places);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", constructeur='" + constructeur + '\'' +
                //", modele='" + modele + '\'' +
                ", nb_places=" + nb_places +
                '}';
    }

    public boolean peutEtreReservée(
            int nbrPlaces) {

        if(nbrPlaces>=2 && nbrPlaces<=9){
            return true;
        }
        return false;

    }


}
