package com.epf.rentmanager.service;



import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service

public class ReservationService {
    private ReservationDao reservationDao;
    //public static ReservationService instance;

    /*private ReservationService() {
        this.reservationDao = ReservationDao.getInstance();
    }*/

    private ReservationService(ReservationDao reservationDao){
        this.reservationDao = reservationDao;
    }

    /*public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }

        return instance;
    }*/


    public long create(Reservation reservation) throws DaoException{
        try {
            return this.reservationDao.create(reservation);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException();

        }
    }

    public void delete(long id) throws DaoException {
        // TODO: créer un véhicule
        try {
            this.reservationDao.delete(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException();

        }

    }
    public void deleteByClientId(long id) throws DaoException {
        // TODO: créer un véhicule
        try {
            this.reservationDao.deleteByClientId(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException();

        }

    }

    public void deletefindResaByVehicleId(long id) throws DaoException {
        // TODO: créer un véhicule
        try {
            this.reservationDao.deletefindResaByVehicleId(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException();

        }

    }
    public List<Reservation> findResaByVehicleId(long id) throws DaoException{
        try {
            return this.reservationDao.findResaByVehicleId(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public List<Reservation>  findResaByClientId(long id) throws DaoException{
        try {
            return this.reservationDao.findResaByClientId( id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Reservation> findAll() throws ServiceException {
        // TODO: récupérer toutes les reservations
        try {
            return this.reservationDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public int compteReservation () throws ServiceException {
        // TODO: récupérer tous les clients
        try {
            return this.reservationDao.compteReservation();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();

        }
    }

    public int compteReservationIdClient(long id) throws DaoException {
        try {
            return this.reservationDao.compteReservationIdClient(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException();

        }

    }

    public List<Reservation>findAllIdC(long idC)  throws DaoException {
        try {
            return this.reservationDao.findAllIdC(idC) ;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException();

        }

    }

    public Reservation findResaById(long idC)  throws DaoException {
        try {
            return this.reservationDao.findResaById(idC) ;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException();

        }

    }

    public int compteVehicleIdClient(long id ) throws DaoException {
        // TODO: récupérer tous les clients
        try {
            return this.reservationDao.compteVehicleIdClient(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException();

        }
    }

    public List<Vehicle> vehicleIdClient(long id ) throws DaoException {
        // TODO: récupérer tous les clients
        try {
            return this.reservationDao.vehicleIdClient(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException();

        }
    }

    public boolean peutEtreReserve(Vehicle V, LocalDate d)throws DaoException {
        // TODO: récupérer tous les clients
        try {
            return this.reservationDao.peutEtreReserve( V,d);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException();

        }
    }

    public void changeById(long id, Reservation r) throws DaoException {
        // TODO: créer un véhicule
        try {
            this.reservationDao.changeById(id, r);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException();

        }

    }
}
