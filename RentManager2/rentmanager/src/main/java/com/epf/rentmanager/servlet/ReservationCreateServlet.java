package com.epf.rentmanager.servlet;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
@WebServlet(name = "ReservationCreateServlet", urlPatterns = "/rents/create")

public class ReservationCreateServlet extends HttpServlet {
    //private static final long serialVersionUID = 1L;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private VehicleService vehicleService;
    private Reservation r;
    private Client c;
    private Vehicle v;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        System.out.println("Resea create servlet OK");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("client", clientService.findAll());
            request.setAttribute("vehicle", vehicleService.findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int idC = Integer.parseInt(request.getParameter("clientS"));
            c = clientService.findById(idC);
            int idV = Integer.parseInt(request.getParameter("car"));
            v = vehicleService.findById(idV);
            LocalDate debut = LocalDate.parse(request.getParameter("begin"));
            LocalDate fin = LocalDate.parse(request.getParameter("end"));
            int jour = Period.between(debut, fin).getDays();
            System.out.println(jour);
            r = new Reservation(v, c, fin, debut);
            List<Reservation> reservations = new ArrayList<Reservation>();
            boolean valeur = true;
            reservations = reservationService.findAll();
            for (int i = 0; i < reservations.size(); i++) {
                if (reservations.get(i).getVehicle().equals(v) &&
                        reservations.get(i).getDebut().equals(debut)) {
                    valeur = false;
                }
            }

            while (!r.etreReserve()
                    || !valeur
                    || !reservationService.moinsDe30Jours(v, jour, debut)) {
                idC = Integer.parseInt(request.getParameter("clientS"));
                c = clientService.findById(idC);
                idV = Integer.parseInt(request.getParameter("car"));
                v = vehicleService.findById(idV);
                debut = LocalDate.parse(request.getParameter("begin"));
                fin = LocalDate.parse(request.getParameter("end"));

                r = new Reservation(v, c, fin, debut);
            }

            request.setAttribute("reservation", reservationService.create(r));
        } catch (DaoException e) {
            throw new RuntimeException(e);

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.doGet(request, response);


    }
}
