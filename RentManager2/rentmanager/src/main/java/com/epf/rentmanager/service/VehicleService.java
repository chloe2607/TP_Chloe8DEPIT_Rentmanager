package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;

public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;
	
	private VehicleService() {
		this.vehicleDao = VehicleDao.getInstance();
	}
	
	public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}
		
		return instance;
	}
	
	
	public long create(Vehicle vehicle) throws DaoException {
		// TODO: créer un véhicule
		try {
			return VehicleDao.getInstance().create(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}
		
	}

	public long delete(Vehicle vehicle) throws DaoException {
		// TODO: créer un véhicule
		try {
			return VehicleDao.getInstance().delete(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}

	}

	public Vehicle findById(long id) throws DaoException{
		// TODO: récupérer un véhicule par son id
		try {
			return VehicleDao.getInstance().findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}
		
	}

	public List<Vehicle> findAll() throws  DaoException {
		// TODO: récupérer tous les clients
		try {
			return VehicleDao.getInstance().findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}
	}

	public int compteVehicle() throws  DaoException {
		// TODO: récupérer tous les clients
		try {
			return VehicleDao.getInstance().compteVehicle();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}
	}
	
}
