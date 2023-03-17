package epf.rentmanager;


import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;

import com.epf.rentmanager.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertThrows;

public class UserServiceTest {
    private ClientDao userDao;
    private ClientService userService;

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
}
