package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

public class ReservationDao {

	private static ReservationDao instance = null;
	private ReservationDao() {}
	public static ReservationDao getInstance() {
		if(instance == null) {
			instance = new ReservationDao();
		}
		return instance;
	}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
		

		public long create(Reservation reservation) throws DaoException {
			long id=0;
			long id_client = reservation.getClient().getIdentifiant();
			long id_vehicle=reservation.getVehicle().getId();

			try {
				Connection connection= ConnectionManager.getConnection();
				PreparedStatement ps = connection.prepareStatement(CREATE_RESERVATION_QUERY , Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1,id_client);
				ps.setLong(2,id_vehicle);
				ps.setDate(3, Date.valueOf(reservation.getDebut()));
				ps.setDate(4, Date.valueOf(reservation.getFin()));
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

	
	public long delete(Reservation reservation) throws DaoException {
		return 0;
	}

	
	public Reservation findResaByClientId(long clientId) throws DaoException {
		Reservation reservation = new Reservation();
		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement preparedstatement = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			preparedstatement.setLong(1,clientId);
			ResultSet rs=preparedstatement.executeQuery();
			while(rs.next()){

				long id =rs.getInt("id");
				long vehicle_id =rs.getInt("vehicle_id");
				LocalDate d= rs.getDate("debut").toLocalDate();
				LocalDate f= rs.getDate("fin").toLocalDate();

				Vehicle v;
				v= VehicleService.getInstance().findById(vehicle_id);
				Client c;
				c=ClientService.getInstance().findById(clientId);

				reservation =new Reservation(v, c, f,  d, id);
				//connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return reservation;
	}

	public Reservation findResaByVehicleId(long vehicleId) throws DaoException {
		Reservation reservation = new Reservation();
		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement preparedstatement = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			preparedstatement.setLong(1,vehicleId);
			ResultSet rs=preparedstatement.executeQuery();
			while(rs.next()){

				long id =rs.getInt("id");
				long client_id =rs.getInt("client_id");
				LocalDate d= rs.getDate("debut").toLocalDate();
				LocalDate f= rs.getDate("fin").toLocalDate();

				Vehicle v;
				v= VehicleService.getInstance().findById(vehicleId);
				Client c;
				c=ClientService.getInstance().findById(client_id);

				reservation =new Reservation(v, c, f,  d, id);
				//connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return reservation;
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservationList = new ArrayList<Reservation>();

		try {
			Connection connection= ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs =statement.executeQuery(FIND_RESERVATIONS_QUERY);
			while(rs.next()){
				long id =rs.getInt("id");
				long client_id =rs.getInt("client_id");
				long vehicle_id =rs.getInt("vehicle_id");
				LocalDate d= rs.getDate("debut").toLocalDate();
				LocalDate f= rs.getDate("fin").toLocalDate();

				Vehicle v;
				v= VehicleService.getInstance().findById(vehicle_id);
						Client c;
				c=ClientService.getInstance().findById(client_id);


				reservationList.add(new Reservation(v, c, f,  d, id));
				//connection.close();
			}


		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return reservationList;


	}

	public int compteReservation() throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		int nbrReservation=0;
		reservations =this.findAll();
		nbrReservation=reservations.size();
		return nbrReservation;
	}
}
