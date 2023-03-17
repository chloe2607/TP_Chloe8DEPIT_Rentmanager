package com.epf.rentmanager.servlet;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UserServlet", urlPatterns = "/users/edit")
public class UserEditServlet extends HttpServlet {
    //private static final long serialVersionUID = 1L;
    @Autowired
    private ClientService clientservice;
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private Client client;

    @Autowired
    private long id;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        System.out.println("User edit servlet OK");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("client", clientservice.findById(Long.parseLong(request.getParameter("id"), 10)));

            //request.setAttribute("identifiant");
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/edit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // try {
        String nom = request.getParameter("last_name");
        String prenom = request.getParameter("first_name");
        LocalDate dateN = LocalDate.parse(request.getParameter("date_nais"));
        String email = request.getParameter("email");
        client = new Client(nom,prenom,dateN,email);
        //int id =request.getParameter("id");
        // c = new Client(id,nom,prenom, dateN,email);

        // request.setAttribute("client", clientservice.create(c));
        // } catch (DaoException e) {
        // throw new RuntimeException(e);
        //}

    }
}
