package io.github.leonidius20.java_web_lab_234.presentation.login;

import io.github.leonidius20.java_web_lab_234.dao.UserDao;
import io.github.leonidius20.java_web_lab_234.domain.User;
import io.github.leonidius20.java_web_lab_234.utils.Cryptography;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class LoginModelTest {

    private final LoginModelImpl model;
    private final User mockUser;

    public LoginModelTest() throws NoSuchAlgorithmException, SQLException {
        var mockedDao = mock(UserDao.class);
        var name = "admin";
        var password = "admin";

        var salt = Cryptography.generateSalt();
        var passwordHash = Cryptography.hashPassword(password, salt);
        this.mockUser = new User(-1, name, "", User.Role.ADMIN, false, passwordHash, salt);
        when(mockedDao.getByName(name)).thenReturn(mockUser);

        this.model = new LoginModelImpl(mockedDao);
    }

    @Test
    void getUserWithCredentialsTest() throws SQLException, NoSuchAlgorithmException {
        assertEquals(mockUser,
                model.getUserWithCredentials("admin", "admin"));
    }

    @Test
    void getUserWithWrongCredentials() throws SQLException, NoSuchAlgorithmException {
        assertNull(model.getUserWithCredentials("admin", "213"));
        assertNull(model.getUserWithCredentials("wrong", "admin"));
    }

}
