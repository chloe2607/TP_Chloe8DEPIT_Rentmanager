package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.stereotype.Repository;

import static com.epf.rentmanager.dao.VehicleDao.*;


@Repository
public class ReservationDao {

	//private static ReservationDao instance = null;

	/*public static ReservationDao getInstance() {
		if(instance == null) {
			instance = new ReservationDao();
		}
		return instance;
	}*/
	private VehicleDao vehicleDao;
	private ClientDao clientDao;
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_BY_ID_QUERY = "SELECT id,vehicle_id, client_id, debut, fin FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";

	private static final String UPDATE_RESERVATION_QUERY = "UPDATE Reservation SET client_id = ?, vehicle_id = ?, debut = ?, fin = ? WHERE id = ?;";

	private ReservationDao(ClientDao cd, VehicleDao vd) {

		clientDao=cd;
		vehicleDao=vd;
	}
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
					System.out.println("l'identifiant est "+id);

				}
				ps.close();
				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
				throw new DaoException();
			}
			return id;
		}

	
	public void delete(long id) throws DaoException {
			//pb id Reservation toujours égal à 0

			Reservation reservation =new Reservation();
			reservation=this.findResaById(id);

			try {
				Connection connection= ConnectionManager.getConnection();
				PreparedStatement ps = connection.prepareStatement(DELETE_RESERVATION_QUERY);
				ps.setLong(1,reservation.getId());
				ps.execute();
				int row =ps.executeUpdate();
				ps.close();
				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
				throw new DaoException();
			}
			//return client.getIdentifiant();
		}

	public void deleteByClientId(long id) throws DaoException {
		//pb id Reservation toujours égal à 0

		Reservation reservation =new Reservation();
		List<Reservation> reservationList = new ArrayList<Reservation>();
		reservationList =this.findResaByClientId(id);

		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_RESERVATION_QUERY);
			for (int i =0; i<reservationList.size();i++){
			ps.setLong(1,reservationList.get(i).getId());}
			ps.execute();
			int row =ps.executeUpdate();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		//return client.getIdentifiant();
	}

	public void deletefindResaByVehicleId(long id) throws DaoException {
		//pb id Reservation toujours égal à 0

		Reservation reservation =new Reservation();
		List<Reservation> reservationList = new ArrayList<Reservation>();
		reservationList =this.findResaByVehicleId(id);

		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_RESERVATION_QUERY);
			for (int i =0; i<reservationList.size();i++){
			ps.setLong(1,reservationList.get(i).getId());}
			ps.execute();
			int row =ps.executeUpdate();
			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		//return client.getIdentifiant();
	}


	
	public List<Reservation>  findResaByClientId(long clientId) throws DaoException {
		Reservation reservation = new Reservation();
		//ClientService clientService=null;
		//VehicleService vehicleService = null;
		List<Reservation> reservationList = new ArrayList<Reservation>();
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
				v= vehicleDao.findById(vehicle_id);
				Client c;
				c=clientDao.findById(clientId);

				reservation =new Reservation(v, c, f,  d, id);
				reservationList.add(reservation);
				//connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return reservationList;
	}

	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		Reservation reservation = new Reservation();

		List<Reservation> reservationList = new ArrayList<Reservation>();

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
				v= vehicleDao.findById(vehicleId);
				Client c;
				c=clientDao.findById(client_id);

				reservation =new Reservation(v, c, f,  d, id);

				reservationList.add(reservation);
				//connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return reservationList;
	}

	public Reservation findResaById(long Id) throws DaoException {
		Reservation reservation = new Reservation();

		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement preparedstatement = connection.prepareStatement(FIND_RESERVATIONS_BY_ID_QUERY );
			preparedstatement.setLong(1,Id);
			ResultSet rs=preparedstatement.executeQuery();
			while(rs.next()){

				long id =rs.getInt("id");
				long vehicle_id =rs.getInt("vehicle_id");
				long client_id =rs.getInt("client_id");
				LocalDate d= rs.getDate("debut").toLocalDate();
				LocalDate f= rs.getDate("fin").toLocalDate();

				Vehicle v;
				v= vehicleDao.findById(vehicle_id);
				Client c;
				c=clientDao.findById(client_id);

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
				v= vehicleDao.findById(vehicle_id);
						Client c;
				c=clientDao.findById(client_id);



				Reservation r=new Reservation(v, c, f,  d, id);
				reservationList.add(r);
				//connection.close();
			}


		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return reservationList;


	}

	public List<Reservation> findAllIdC(long idC) throws DaoException {
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
				v= vehicleDao.findById(vehicle_id);
				Client c;
				c=clientDao.findById(client_id);
				if(idC==client_id) {

					reservationList.add(new Reservation(v, c, f, d, id));
				}
				//connection.close();
			}


		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return reservationList;


	}

	public List<Vehicle> findAllVehicleIdC(long idC) throws DaoException {
		List<Vehicle> vehiclesList = new ArrayList<Vehicle>();


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
				v= vehicleDao.findById(vehicle_id);
				Client c;
				c=clientDao.findById(client_id);
				if(idC==client_id) {

					vehiclesList.add(v);
				}
				//connection.close();
			}


		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return vehiclesList;


	}

	public int compteReservation() throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		int nbrReservation=0;
		reservations =this.findAll();
		nbrReservation=reservations.size();
		return nbrReservation;
	}

	public int compteReservationIdClient(long id) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		reservations=this.findAllIdC(id);
		int nbrReservation=0;
		nbrReservation=reservations.size();
		return nbrReservation;
	}

	public int compteVehicleIdClient(long id) throws DaoException{
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		vehicles=this.vehicleIdClient(id);
		return vehicles.size();
	}

	public List<Vehicle> vehicleIdClient(long id) throws DaoException{
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		vehicles=this.findAllVehicleIdC(id);
		for(int i=0; i<vehicles.size()-1; i++){
			for(int j=i+1; j<vehicles.size(); j++){
				if(vehicles.get(i)==vehicles.get(j)){
					vehicles.remove(i);
				}

			}
		}
		return vehicles;
	}

	public boolean peutEtreReserve(Vehicle V,LocalDate d) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		reservations=this.findAll();
		for (int i=0; i<reservations.size();i++) {
			if (reservations.get(i).getVehicle().equals(V) &&
					reservations.get(i).getDebut().equals(d)) {
				return false;
			}
		}
		return true;
	}

	public void changeById(long id, Reservation r)throws DaoException{
		try {
			Connection connection= ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(UPDATE_RESERVATION_QUERY );
			ps.setLong(5,id);

			ps.setLong(1,r.getClient().getIdentifiant());
			ps.setLong(2,r.getVehicle().getId());
			ps.setDate(3,Date.valueOf(r.getDebut()));
			ps.setDate(4, Date.valueOf(r.getFin()));
			//ResultSet rs=ps.executeQuery();
			ps.execute();

			ps.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}

	}

	public boolean moinsDe30Jours(Vehicle v, int temps, LocalDate debut) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		reservations = this.findAll();
		int duree = 0;
		if (reservations.get(0).getVehicle().getId() == v.getId()) {
			duree = duree + Period.between(reservations.get(0).getDebut(), reservations.get(0).getFin()).getDays();
			if (duree >= 30) {
				return false;
			}
		}
			for (int i = 1; i < reservations.size(); i++) {
				if (reservations.get(i).getVehicle().getId() == v.getId()) {
					if (reservations.get(i - 1).getFin().equals(reservations.get(i).getDebut()) && reservations.get(i - 1).getVehicle().getId() == reservations.get(i).getVehicle().getId()) {
						duree = duree + Period.between(reservations.get(i).getDebut(), reservations.get(i).getFin()).getDays();

					}

					if (reservations.get(i).getFin().equals(debut)) {
						duree = duree + Period.between(reservations.get(i).getDebut(), reservations.get(i).getFin()).getDays();

					}
					if (duree >= 30) {
						return false;
					}
				}
			}


			if (duree + temps >= 30) {
				return false;


			}
			return true;
		}

	}
