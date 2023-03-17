package com.epf.rentmanager.servlet;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UserDetailServlet", urlPatterns = "/users/details")
public class UserDetailServlet extends HttpServlet{
    //private static final long serialVersionUID = 1L;
    @Autowired
    private ClientService clientservice;
    @Autowired
    private ReservationService reservationService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        System.out.println("User detail servlet OK");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {

            request.setAttribute("client", clientservice.findById(Long.parseLong(request.getParameter("id"),10)));
           request.setAttribute("nbReservation", reservationService.compteReservationIdClient(Long.parseLong(request.getParameter("id"),10)));
            request.setAttribute("reservations", reservationService.findAllIdC(Long.parseLong(request.getParameter("id"),10)));
            request.setAttribute("nbVehicles", reservationService.compteVehicleIdClient(Long.parseLong(request.getParameter("id"),10)));
            request.setAttribute("Vehicles", reservationService.vehicleIdClient(Long.parseLong(request.getParameter("id"),10)));




        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request, response);
       }
}
//pas lié à details
