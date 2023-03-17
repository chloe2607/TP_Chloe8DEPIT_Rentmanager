package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

	@Autowired
	private ReservationService reservationService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private VehicleService vehicleService;
	//private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{

			int nbrClients =clientService.compteClient();
			request.setAttribute("nbrClients", nbrClients);
			int nbrvehicle =vehicleService.compteVehicle();
			request.setAttribute("nbrvehicle", nbrvehicle);
			int nbrReservations =reservationService.compteReservation();
			request.setAttribute("nbrReservations", nbrReservations);
		}
		catch (DaoException e) {
		throw new RuntimeException(e);

		}


		this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}

}
