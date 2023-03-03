package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;

public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;
	
	private ClientService() {
		this.clientDao = ClientDao.getInstance();
	}
	
	public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}
		
		return instance;
	}
	
	
	public long create(Client client) throws DaoException{
		try {
			return ClientDao.getInstance().create(client);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}
	}

	public Client findById(long id) throws DaoException{
		// TODO: récupérer un client par son id
		try {
			return ClientDao.getInstance().findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}
	}

	public List<Client> findAll() throws DaoException {
		// TODO: récupérer tous les clients
		try {
			return ClientDao.getInstance().findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new DaoException();

		}
	}

		public int compteClient () throws DaoException {
			// TODO: récupérer tous les clients
			try {
				return ClientDao.getInstance().compteClient();
			} catch (DaoException e) {
				e.printStackTrace();
				throw new DaoException();

			}
		}

	}
