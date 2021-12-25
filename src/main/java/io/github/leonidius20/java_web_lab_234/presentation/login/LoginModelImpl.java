package io.github.leonidius20.java_web_lab_234.presentation.login;

import io.github.leonidius20.java_web_lab_234.dao.UserDao;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;
import io.github.leonidius20.java_web_lab_234.domain.User;
import io.github.leonidius20.java_web_lab_234.utils.Cryptography;

import javax.sql.DataSource;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginModelImpl {

    private final DataSource connection = DatabaseConnection.get();

    /**
     * @return user if the credentials are correct, null otherwise
     */
    public User getUserWithCredentials(String name, String password) throws SQLException, NoSuchAlgorithmException {
        var dao = new UserDao(connection.getConnection());
        var user = dao.getByName(name);
        if (user == null) return null;

        Logger.getLogger("Password").log(Level.INFO, "User is not null");
        byte[] providedPasswordHash = Cryptography.hashPassword(password, user.salt());
        if (Arrays.equals(providedPasswordHash, user.passwordHash()))
            return user;

        return null;
    }

}
