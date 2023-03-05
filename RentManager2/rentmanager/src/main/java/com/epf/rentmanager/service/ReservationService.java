package com.epf.rentmanager.service;



import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;

import java.util.List;

public class ReservationService {
    private ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService() {
        this.reservationDao = ReservationDao.getInstance();
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }

        return instance;
    }


    public long create(Reservation reservation) throws DaoException{
        try {
            return reservationDao.getInstance().create(reservation);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException();

        }
    }

    public void delete(long id) throws DaoException {
        // TODO: créer un véhicule
        try {
            ReservationDao.getInstance().deleteByClientId(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException();

        }

    }
    public Reservation findResaByVehicleId(long id) throws DaoException{
        try {
            return ReservationDao.getInstance().findResaByVehicleId(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public Reservation findResaByClientId(long id) throws DaoException{
        try {
            return ReservationDao.getInstance().findResaByClientId( id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Reservation> findAll() throws DaoException {
        // TODO: récupérer toutes les reservations
        try {
            return ReservationDao.getInstance().findAll();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int compteReservation () throws DaoException {
        // TODO: récupérer tous les clients
        try {
            return ReservationDao.getInstance().compteReservation();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException();

        }
    }
}
