package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{

			int nbrClients =ClientService.getInstance().compteClient();
			request.setAttribute("nbrClients", nbrClients);
			int nbrvehicle =VehicleService.getInstance().compteVehicle();
			request.setAttribute("nbrvehicle", nbrvehicle);
			int nbrReservations =ReservationService.getInstance().compteReservation();
			request.setAttribute("nbrReservations", nbrReservations);
		}
		catch (DaoException e) {
		throw new RuntimeException(e);

		}


		this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}

}
