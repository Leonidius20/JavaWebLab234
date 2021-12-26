package io.github.leonidius20.java_web_lab_234.presentation.unban_user;

import io.github.leonidius20.java_web_lab_234.dao.UserDao;
import io.github.leonidius20.java_web_lab_234.dao.UserDaoImpl;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;

import java.sql.SQLException;

public class UnbanUserModel {

    private final UserDao dao;

    public UnbanUserModel() throws SQLException {
        dao = new UserDaoImpl(DatabaseConnection.get().getConnection());
    }

    public void unbanUser(int id) throws SQLException {
        dao.setUserBanned(id, false);
    }

}
