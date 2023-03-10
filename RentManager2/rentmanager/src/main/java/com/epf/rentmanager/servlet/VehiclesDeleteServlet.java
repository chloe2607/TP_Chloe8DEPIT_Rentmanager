package com.epf.rentmanager.servlet;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

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
@WebServlet("/vehicles/delete")
public class VehiclesDeleteServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private VehicleService vehicleservice= VehicleService.getInstance();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int id= Integer.parseInt(request.getParameter("id"));
            vehicleservice.delete(id);
            response.sendRedirect("/rentmanager/vehicles");
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }




    }
}
