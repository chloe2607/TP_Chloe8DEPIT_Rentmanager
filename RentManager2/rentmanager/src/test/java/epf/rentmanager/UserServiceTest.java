package epf.rentmanager;


import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;

import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertThrows;

public class UserServiceTest {
    private ClientDao userDao;
    private ClientService userService;
    private ReservationService rentService;
    private ReservationDao  rentDao;
    private VehicleService vehicleService;
    private VehicleDao vehicleDao;

    @BeforeEach
    void setUp(){
        userDao = mock(ClientDao.class);
        userService = new ClientService(userDao);
    }

    @Test
    void findAll_should_fail_when_dao_throws_exception() throws DaoException {
        when(this.userDao.findAll()).thenThrow(DaoException.class);

        assertThrows(ServiceException.class, () -> userService.findAll());
    }

    @Test
    void compteClient_should_fail_when_dao_throws_exception() throws DaoException {
        when(this.userDao.compteClient()).thenThrow(DaoException.class);

        assertThrows(ServiceException.class, () -> userService.compteClient());
    }

    @Test
    void findAllR_should_fail_when_dao_throws_exception() throws DaoException {
        when(this.rentDao.findAll()).thenThrow(DaoException.class);

        assertThrows(ServiceException.class, () -> rentService.findAll());
    }

    @Test
    void compteReservation_should_fail_when_dao_throws_exception() throws DaoException {
        when(this.rentDao.compteReservation ()).thenThrow(DaoException.class);

        assertThrows(ServiceException.class, () -> rentService.compteReservation ());
    }

    @Test
    void findAllV_should_fail_when_dao_throws_exception() throws DaoException {
        when(this.vehicleDao.findAll()).thenThrow(DaoException.class);

        assertThrows(ServiceException.class, () -> vehicleService.findAll());
    }

    @Test
    void compteVehicle_should_fail_when_dao_throws_exception() throws DaoException {
        when(this.vehicleDao.compteVehicle()).thenThrow(DaoException.class);

        assertThrows(ServiceException.class, () -> vehicleService.compteVehicle());
    }
}
