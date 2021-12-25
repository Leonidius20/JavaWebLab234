package io.github.leonidius20.java_web_lab_234.presentation.login;

import io.github.leonidius20.java_web_lab_234.dao.UserDao;
import io.github.leonidius20.java_web_lab_234.dao.UserDaoImpl;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;
import io.github.leonidius20.java_web_lab_234.domain.User;
import io.github.leonidius20.java_web_lab_234.utils.Cryptography;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;

public class LoginModelImpl {

    private final UserDao dao;

    public LoginModelImpl() throws SQLException {
        this.dao = new UserDaoImpl(DatabaseConnection.get().getConnection());
    }

    // for testing
    public LoginModelImpl(UserDao dao) {
        this.dao = dao;
    }

    /**
     * @return user if the credentials are correct, null otherwise
     */
    public User getUserWithCredentials(String name, String password) throws SQLException, NoSuchAlgorithmException {
        var user = dao.getByName(name);
        if (user == null) return null;

        byte[] providedPasswordHash = Cryptography.hashPassword(password, user.salt());
        if (Arrays.equals(providedPasswordHash, user.passwordHash()))
            return user;

        return null;
    }

}
