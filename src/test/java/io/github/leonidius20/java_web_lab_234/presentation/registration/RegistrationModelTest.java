package io.github.leonidius20.java_web_lab_234.presentation.registration;

import io.github.leonidius20.java_web_lab_234.dao.UserDao;
import io.github.leonidius20.java_web_lab_234.domain.User;
import io.github.leonidius20.java_web_lab_234.presentation.auth.registration.RegistrationModelImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class RegistrationModelTest {

    private final UserDao mockedDao;
    private final RegistrationModelImpl model;

    public RegistrationModelTest() throws SQLException {
        this.mockedDao = mock(UserDao.class);



        this.model = new RegistrationModelImpl(mockedDao);
    }

    @Test
    public void validUserTest() throws SQLException, NoSuchAlgorithmException {
        when(mockedDao.insertUser(any())).thenReturn(1);

        String name = "name";
        String passport = "AA0000";
        String password = "12345";

        var result = model.createUser(name, passport, "user", password);

        var captor = ArgumentCaptor.forClass(User.class);
        verify(mockedDao).insertUser(captor.capture());
        var createdUser = captor.getValue();

        assertEquals(-1, createdUser.id());
        assertEquals(name, createdUser.name());
        assertEquals(passport, createdUser.passportNumber());
        assertEquals(User.Role.USER, createdUser.role());

        assertEquals(1, result.id());
    }

    @Test
    public void invalidUserTest() throws SQLException, NoSuchAlgorithmException {
        when(mockedDao.insertUser(any())).thenReturn(-1);
        assertNull(model.createUser("", "", "user", ""));
    }

}
