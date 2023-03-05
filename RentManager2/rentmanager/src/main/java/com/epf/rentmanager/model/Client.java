package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.util.Objects;

public class Client {
    private long identifiant;
    private String nom;
    private String prenom;
    private String adresseMail;
    private LocalDate  dateN;

    public Client(String nom, String prenom, long id, LocalDate d, String adm ){
        this.nom=nom;
        this.prenom=prenom;
        identifiant=id;
        dateN=d;
        adresseMail=adm;
    }

    public Client(String nom, String prenom,LocalDate d,String adm ){
        this.nom=nom;
        this.prenom=prenom;
        dateN=d;
        adresseMail=adm;
    }
    public Client(){

    }

    public long getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(long identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public LocalDate getDateN() {
        return dateN;
    }

    public void setDateN(LocalDate dateN) {
        this.dateN = dateN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Double.compare(client.identifiant, identifiant) == 0 && Objects.equals(nom, client.nom) && Objects.equals(prenom, client.prenom) && Objects.equals(adresseMail, client.adresseMail) && Objects.equals(dateN, client.dateN);
    }

    @Override
    public int hashCode() {
        //super identifiant
        return Objects.hash(identifiant, nom, prenom, adresseMail, dateN);
    }

    @Override
    public String toString() {
        return "Clients{" +
                "identifiant=" + identifiant +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresseMail='" + adresseMail + '\'' +
                ", dateN=" + dateN +
                '}';
    }
}
