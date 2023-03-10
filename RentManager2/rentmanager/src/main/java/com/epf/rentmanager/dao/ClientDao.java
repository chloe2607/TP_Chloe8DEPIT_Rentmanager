package com.epf.rentmanager.dao;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;


public class ClientDao {
	
	private static ClientDao instance = null;
	private ClientDao() {}
	public static ClientDao getInstance() {
		if(instance == null) {
			instance = new ClientDao();
		}
		return instance;
	}
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";


	public long create(Client client) throws DaoException {
		long id=0;
		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(CREATE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,client.getNom());
			ps.setString(2,client.getPrenom());
			ps.setString(3,client.getAdresseMail());
			ps.setDate(4, Date.valueOf(client.getDateN()));

			ps.execute();
			ResultSet resultSet = ps.getGeneratedKeys();

			if (resultSet.next()) {
				id = resultSet.getInt(1);

			}
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return id;
	}
	
	public long delete(Client client) throws DaoException {
		return 0;
	}

	public Client findById(long id) throws DaoException {
		Client clt= new Client();
		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement preparedstatement = connection.prepareStatement(FIND_CLIENT_QUERY);
			preparedstatement.setLong(1,id);
			ResultSet rs=preparedstatement.executeQuery();
			while(rs.next()){

				String nom= rs.getString("nom");
				String prenom = rs.getString("prenom");
				LocalDate d= rs.getDate("naissance").toLocalDate();
				String am =rs.getString("email");

				Client c =new Client(nom,  prenom,  id,d, am);

				clt=c;
			}
				connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return clt;
	}

	public List<Client> findAll() throws DaoException {
		List<Client> clients = new ArrayList<Client>();
		try {
			Connection connection= ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs =statement.executeQuery(FIND_CLIENTS_QUERY);
			while(rs.next()){
				long id =rs.getInt("id");
				String nom= rs.getString("nom");
				String prenom = rs.getString("prenom");
				LocalDate d= rs.getDate("naissance").toLocalDate();
				String am =rs.getString("email");

				clients.add(new Client(nom,  prenom,  id,d, am));
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return clients;
	}

	public int compteClient() throws DaoException {
		List<Client> clients = new ArrayList<Client>();
		int nbrClient=0;
		clients =this.findAll();
		nbrClient=clients.size();
		return nbrClient;
	}



}
