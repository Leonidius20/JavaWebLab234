package io.github.leonidius20.java_web_lab_234.presentation.readers;

import io.github.leonidius20.java_web_lab_234.dao.UserWithRequestsDaoImpl;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;
import io.github.leonidius20.java_web_lab_234.domain.UserWithRequests;

import java.sql.SQLException;
import java.util.List;

public class ReadersModel {

    private final UserWithRequestsDaoImpl dao;

    public ReadersModel() throws SQLException {
        dao = new UserWithRequestsDaoImpl(DatabaseConnection.get().getConnection());
    }

    public ReadersModel(UserWithRequestsDaoImpl dao) {
        this.dao = dao;
    }

    public List<UserWithRequests> getUsers() throws SQLException {
        return dao.getUsersWithRequests();
    }

}
