package com.epf.rentmanager.servlet;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/rents/create")
public class ReservationCreateServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private ReservationService reservationService= ReservationService.getInstance();
    private ClientService clientService=ClientService.getInstance();
    private VehicleService vehicleService=VehicleService.getInstance();
    private Reservation r;
    private Client c;
    private Vehicle v;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("client", clientService.findAll());
            request.setAttribute("vehicle", vehicleService.findAll());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int idC= Integer.parseInt(request.getParameter("clientS"));
            c= clientService.findById(idC);
            int idV= Integer.parseInt(request.getParameter("car"));
            v=vehicleService.findById(idV);
            LocalDate debut= LocalDate.parse(request.getParameter("begin"));
            LocalDate fin= LocalDate.parse(request.getParameter("end"));

            r=new Reservation(v,c,fin,debut);

            request.setAttribute("reservation", reservationService.create(r));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        this.doGet(request,response);
    }

}
