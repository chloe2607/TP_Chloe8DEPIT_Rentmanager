package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReservationDetailServlet", urlPatterns = "/rents/details")
public class ReservationDetailsServlet extends HttpServlet {
    @Autowired
    private ClientService clientservice;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        System.out.println("Rents detail servlet OK");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {

            request.setAttribute("reservation", reservationService.findResaById(Long.parseLong(request.getParameter("id"),10)));
            //request.setAttribute("nbReservation", reservationService.compteReservationIdClient(Long.parseLong(request.getParameter("id"),10)));
            //request.setAttribute("reservations", reservationService.findAllIdC(Long.parseLong(request.getParameter("id"),10)));
            //request.setAttribute("nbVehicles", reservationService.compteVehicleIdClient(Long.parseLong(request.getParameter("id"),10)));
            //request.setAttribute("Vehicles", reservationService.vehicleIdClient(Long.parseLong(request.getParameter("id"),10)));




        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/details.jsp").forward(request, response);
    }
}
