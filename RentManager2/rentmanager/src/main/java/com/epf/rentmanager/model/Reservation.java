package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Reservation {
    private long id;
    private LocalDate debut;
    private LocalDate fin;
    private Client client;
    private Vehicle vehicle;
    //public Reservation(long id, ){

    //}
    public Reservation(){

    }
public Reservation(Vehicle v, Client c, LocalDate f, LocalDate d, long id){
    vehicle =v;
    client =c;
    fin=f;
    debut=d;
    this.id=id;
}

    public Reservation(Vehicle v, Client c, LocalDate f, LocalDate d){
        vehicle =v;
        client =c;
        fin=f;
        debut=d;

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id == that.id && Objects.equals(debut, that.debut) && Objects.equals(fin, that.fin) && Objects.equals(client, that.client) && Objects.equals(vehicle, that.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, debut, fin, client, vehicle);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", debut=" + debut +
                ", fin=" + fin +
                ", client=" + client +
                ", vehicle=" + vehicle +
                '}';
    }

    public boolean etreReserve() {
        // validate inputs ...
        int jour = Period.between(debut, fin).getDays();
        if(jour<=7){
            return true;
        }
        return false;

    }
}
