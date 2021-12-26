package io.github.leonidius20.java_web_lab_234.presentation.users.users_list;

import io.github.leonidius20.java_web_lab_234.dao.UserDao;
import io.github.leonidius20.java_web_lab_234.dao.UserDaoImpl;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;
import io.github.leonidius20.java_web_lab_234.domain.User;

import java.sql.SQLException;
import java.util.List;

public class UsersModel {

    private final UserDao dao;

    public UsersModel() throws SQLException {
        dao = new UserDaoImpl(DatabaseConnection.get().getConnection());
    }

    public List<User> getUsers() throws SQLException {
        return dao.getByRole(User.Role.USER);
    }

}
