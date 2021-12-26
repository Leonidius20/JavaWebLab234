package io.github.leonidius20.java_web_lab_234.presentation.auth.registration;

import io.github.leonidius20.java_web_lab_234.dao.UserDao;
import io.github.leonidius20.java_web_lab_234.dao.UserDaoImpl;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;
import io.github.leonidius20.java_web_lab_234.domain.User;
import io.github.leonidius20.java_web_lab_234.utils.Cryptography;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class RegistrationModelImpl {

    private final UserDao dao;

    public RegistrationModelImpl() throws SQLException {
        dao = new UserDaoImpl(DatabaseConnection.get().getConnection());
    }

    public RegistrationModelImpl(UserDao dao) { this.dao = dao; }

    /**
     * @return created user or null if failed
     */
    public User createUser(String name, String passportNum, String roleStr, String password) throws SQLException, NoSuchAlgorithmException {
        var role = User.Role.valueOf(roleStr.toUpperCase());

        var salt = Cryptography.generateSalt();
        var passwordHash = Cryptography.hashPassword(password, salt);

        var user = new User(-1, name, passportNum, role, false, passwordHash, salt);

        int id = dao.insertUser(user);

        if (id == -1) return null;

        return user.copyWithId(id);
    }

}
