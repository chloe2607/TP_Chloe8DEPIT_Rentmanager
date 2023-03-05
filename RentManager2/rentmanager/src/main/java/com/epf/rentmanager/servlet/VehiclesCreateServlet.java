package com.epf.rentmanager.servlet;


import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/vehicles/create")
public class VehiclesCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VehicleService vehicleservice=VehicleService.getInstance();
    private Vehicle v;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String constructeur=request.getParameter("constructeur");
           int nb_places = Integer.parseInt(request.getParameter("seats"));

            v=new Vehicle(constructeur, nb_places);

            request.setAttribute("vehicle", vehicleservice.create(v));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        this.doGet(request,response);
    }
}
