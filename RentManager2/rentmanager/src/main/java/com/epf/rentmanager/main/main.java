package com.epf.rentmanager.main;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.time.LocalDate;
import java.util.List;

public class main{

    public static void main(String [] arg){
        String nom="dede";
        String prenom="cloclo";
        long id =9;
        LocalDate d = LocalDate.of(1980, 4, 9);
        LocalDate f = LocalDate.of(1990, 4, 9);
        String am="dede.cloclo@epf.fr";
        Client c =new Client(nom,  prenom,  id,d, am);
      /*System.out.println(c);
        try {
          System.out.println(ClientService.getInstance().findAll());
        } catch (DaoException e) {

           e.printStackTrace();
        }*/
        System.out.println(c);
        try {
          System.out.println(ClientService.getInstance().findById(3));
        } catch (DaoException e) {

           e.printStackTrace();
        }

        try {
            System.out.println(ClientService.getInstance().compteClient());
        } catch (DaoException e) {

            e.printStackTrace();
        }
        /*try {
            System.out.println(ClientService.getInstance().delete(7));
        } catch (DaoException e) {

            e.printStackTrace();
        }*/
        /*try {
            System.out.println(ClientService.getInstance().compteClient());
        } catch (DaoException e) {

            e.printStackTrace();
        }/*

        /*try {
            System.out.println(VehicleService.getInstance().compteVehicle());
        } catch (DaoException e) {

            e.printStackTrace();
        }

        try {
            System.out.println(ReservationService.getInstance().compteReservation());
        } catch (DaoException e) {

            e.printStackTrace();
        }*/
/*
        try {
            System.out.println(ClientService.getInstance().create(c));
        } catch (DaoException e) {

            e.printStackTrace();
        }

        try {
            System.out.println(ClientService.getInstance().findAll());
        } catch (DaoException e) {

            e.printStackTrace();
        }*/

       /*try {
           System.out.println(ClientService.getInstance().findById(2));


       } catch (DaoException e) {
            throw new RuntimeException(e);
        }

*/
       String cst="Renault";

        int vid=9;
        int nbP=5;
        Vehicle v = new Vehicle( vid, cst, nbP);
        System.out.println(v);
/*
        try {
            System.out.println(VehicleService.getInstance().findAll());
        } catch (DaoException e) {

            e.printStackTrace();
        }




/*
       try {
            System.out.println(VehicleService.getInstance().create(v));
        } catch (DaoException e) {

            e.printStackTrace();
        }

        try {
            System.out.println(VehicleService.getInstance().findAll());
        } catch (DaoException e) {

            e.printStackTrace();
        }

        try {
            System.out.println(VehicleService.getInstance().delete(v));
        } catch (DaoException e) {

            e.printStackTrace();
        }

        try {
            System.out.println(VehicleService.getInstance().findAll());
        } catch (DaoException e) {

            e.printStackTrace();
        }*/


     Reservation R = new Reservation(v,  c,  f, d,  id);
        System.out.println(R);
        try {
            System.out.println(ReservationService.getInstance().findAll());
        } catch (DaoException e) {

            e.printStackTrace();
        }
        try {
            System.out.println(ReservationService.getInstance().vehicleIdClient(9));
        } catch (DaoException e) {

            e.printStackTrace();
        }

        /*try {
            System.out.println(ReservationService.getInstance().findAll());
        } catch (DaoException e) {

            e.printStackTrace();
        }

       try {
           System.out.println("le nbr de voiture pour le client est :");
            System.out.println("le nb est "+ReservationService.getInstance().compteVehicleIdClient(2));
        } catch (DaoException e) {

            e.printStackTrace();
        }
System.out.println("c'est la fin");
        /*try {
            System.out.println(ReservationService.getInstance().findResaByVehicleId(9));
        } catch (DaoException e) {

            e.printStackTrace();
        }
*/
        /*try {
            System.out.println(ReservationService.getInstance().compteReservation());
        } catch (DaoException e) {

            e.printStackTrace();
        }*/

        /*try {
            System.out.println(ReservationService.getInstance().delete());
        } catch (DaoException e) {

            e.printStackTrace();
        }*/
        /*try {
            System.out.println(ReservationService.getInstance().compteReservation());
        } catch (DaoException e) {

            e.printStackTrace();
        }*/

// id des reservations n'augmente pas

      /*  try {
            System.out.println(ReservationDao.getInstance().findAll());
        } catch (DaoException e) {

            e.printStackTrace();
        }*/

    }


}
