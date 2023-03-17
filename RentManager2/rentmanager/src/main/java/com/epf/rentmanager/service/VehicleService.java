package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

	private VehicleDao vehicleDao;
	//public static VehicleService instance;

	/*private VehicleService() {
		this.vehicleDao = VehicleDao.getInstance();
	}

	public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}

		return instance;
	}*/

	private VehicleService(VehicleDao vehicleDao){
		this.vehicleDao = vehicleDao;
	}


	public long create(Vehicle vehicle) throws DaoException {
		// TODO: créer un véhicule
		try {
			return this.vehicleDao.create(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}

	}

	public long delete(Vehicle vehicle) throws DaoException {
		// TODO: créer un véhicule
		try {
			return this.vehicleDao.delete(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}

	}

	public void delete(long id) throws DaoException {
		// TODO: créer un véhicule
		try {
			this.vehicleDao.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}

	}

	public Vehicle findById(long id) throws DaoException {
		// TODO: récupérer un véhicule par son id
		try {
			return this.vehicleDao.findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}

	}

	public List<Vehicle> findAll() throws DaoException {
		// TODO: récupérer tous les clients
		try {
			return this.vehicleDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}
	}

	public int compteVehicle() throws DaoException {
		// TODO: récupérer tous les clients
		try {
			return this.vehicleDao.compteVehicle();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}
	}


}

