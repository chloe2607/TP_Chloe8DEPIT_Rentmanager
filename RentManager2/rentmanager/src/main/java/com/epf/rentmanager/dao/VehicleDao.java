package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleDao {
	
	//private static VehicleDao instance = null;
	private VehicleDao() {

	}
	/*public static VehicleDao getInstance() {
		if(instance == null) {
			instance = new VehicleDao();
		}
		return instance;
	}*/
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";

	private static final String UPDATE_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur = ?, nb_places = ? WHERE id = ?;";

	public long create(Vehicle vehicle) throws DaoException {

		long id=0;
		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(CREATE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,vehicle.getConstructeur());
			ps.setInt(2,vehicle.getNb_places());

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



	public long delete(Vehicle vehicle) throws DaoException {
		//execute or executeUpdate instead of executeQuery
		long id=0;
		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_VEHICLE_QUERY);
			ps.setLong(1,vehicle.getId());
			ps.execute();
			int row =ps.executeUpdate();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return vehicle.getId();
	}

	public void delete(long id) throws DaoException {
		Vehicle v=new Vehicle();
		v=this.findById(id);

		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_VEHICLE_QUERY);
			ps.setLong(1,v.getId());
			ps.execute();
			int row =ps.executeUpdate();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public Vehicle findById(long id) throws DaoException {
		Vehicle v=null;
		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement preparedstatement = connection.prepareStatement(FIND_VEHICLE_QUERY);
			preparedstatement.setLong(1,id);
			ResultSet rs=preparedstatement.executeQuery();
			while(rs.next()){
				String cst= rs.getString("constructeur");

				int nbP= rs.getInt("nb_places");

				v =new Vehicle(id, cst, nbP);

			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return v;

	}

	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> vehicules = new ArrayList<Vehicle>();
		try {
			Connection connection= ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs =statement.executeQuery(FIND_VEHICLES_QUERY);
			while(rs.next()) {
				long id = rs.getInt("id");
				String cst = rs.getString("constructeur");

				int nbP = rs.getInt("nb_places");

				vehicules.add(new Vehicle(id, cst, nbP));
			}connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return vehicules;
		
	}

	public int compteVehicle() throws DaoException {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		int nbrVehicle=0;
		vehicles =this.findAll();
		nbrVehicle=vehicles.size();
		return nbrVehicle;
	}

	public void changeById(long id, Vehicle v)throws DaoException{
		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(UPDATE_VEHICLE_QUERY);
			ps.setLong(3,id);

			ps.setString(1,v.getConstructeur());
			ps.setInt(2,v.getNb_places());
			//ResultSet rs=ps.executeQuery();
			ps.execute();

			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}

	}
	

}
