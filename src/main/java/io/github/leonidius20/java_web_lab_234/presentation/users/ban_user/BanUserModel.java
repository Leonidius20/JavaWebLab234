package io.github.leonidius20.java_web_lab_234.presentation.users.ban_user;

import io.github.leonidius20.java_web_lab_234.dao.UserDao;
import io.github.leonidius20.java_web_lab_234.dao.UserDaoImpl;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;

import java.sql.SQLException;

public class BanUserModel {

    private final UserDao dao;

    public BanUserModel() throws SQLException {
        dao = new UserDaoImpl(DatabaseConnection.get().getConnection());
    }

    public BanUserModel(UserDao dao) {
        this.dao = dao;
    }

    public void banUser(int id) throws SQLException {
        dao.setUserBanned(id, true);
    }

}
