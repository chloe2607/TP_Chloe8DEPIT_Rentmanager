package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/users/create")

public class UserCreateServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private ClientService clientservice=ClientService.getInstance();
    private Client c;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String nom=request.getParameter("last_name");
            String prenom=request.getParameter("first_name");
            LocalDate dateN = LocalDate.parse(request.getParameter("date_nais"));
            String email=request.getParameter("email");
           c=new Client(nom,prenom, dateN,email);

            request.setAttribute("client", clientservice.create(c));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        this.doGet(request,response);
    }
}
