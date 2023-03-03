package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.DaoException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/users/create")

public class UserCreateServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            request.setAttribute("client", clientservice.findById(Long.parseLong(request.getParameter("id"),10)));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        this.doGet(request,response);
    }
}
