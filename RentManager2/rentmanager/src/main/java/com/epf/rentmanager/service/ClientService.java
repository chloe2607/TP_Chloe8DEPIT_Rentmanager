package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

	private ClientDao clientDao;
	//public static ClientService instance;

	/*private ClientService() {
		this.clientDao = ClientDao.getInstance();
	}*/
	public ClientService(ClientDao clientDao){
		this.clientDao = clientDao;
	}
	/*public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}
		
		return instance;
	}*/
	
	
	public long create(Client client) throws DaoException{
		try {
			return this.clientDao.create(client);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}
	}

	public void delete(long id) throws DaoException {
		// TODO: créer un véhicule
		try {
			this.clientDao.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}

	}

	public Client findById(long id) throws DaoException{
		// TODO: récupérer un client par son id
		try {
			return this.clientDao.findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}
	}

	public List<String> findAllAdresMail() throws DaoException{
		// TODO: récupérer un client par son id
		try {
			return this.clientDao.findAllAdresMail();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}
	}

	public boolean adresMailExisteDeja(String adresMail) throws DaoException{
		// TODO: récupérer un client par son id
		try {
			return this.clientDao.adresMailExisteDeja(adresMail);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}
	}

	public List<Client> findAll() throws ServiceException {
		// TODO: récupérer tous les clients
		try {
			return this.clientDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();

		}
	}

		public int compteClient () throws ServiceException{
			// TODO: récupérer tous les clients
			try {
				return this.clientDao.compteClient();
			} catch (DaoException e) {
				e.printStackTrace();
				throw new ServiceException();

			}
		}

	public void  changeById(long id, Client c) throws DaoException {
		// TODO: récupérer tous les clients
		try {
			this.clientDao.changeById(id, c);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}
	}



	}
