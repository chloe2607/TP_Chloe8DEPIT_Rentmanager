package com.epf.rentmanager.servlet;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
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

@WebServlet(name = "UserDeleteServlet", urlPatterns = "/users/delete")

public class UserDeleteServlet extends HttpServlet{
    //private static final long serialVersionUID = 1L;
    @Autowired
    private ClientService clientservice;

    @Autowired
    private ReservationService resaService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        System.out.println("User delete servlet OK");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Reservation> reservationList = new ArrayList<Reservation>();
            //request.setAttribute("reservation", resaService.findAll());
            int id= Integer.parseInt(request.getParameter("id"));
            reservationList=resaService.findResaByClientId(id);
           resaService.deleteByClientId(id);
            clientservice.delete(id);
           response.sendRedirect("/rentmanager/users");
        } catch (DaoException  e) {
            throw new RuntimeException(e);
        }

    }

}
