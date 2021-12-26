package io.github.leonidius20.java_web_lab_234.presentation.librarians.librarians_list;

import io.github.leonidius20.java_web_lab_234.dao.UserDao;
import io.github.leonidius20.java_web_lab_234.dao.UserDaoImpl;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;
import io.github.leonidius20.java_web_lab_234.domain.User;

import java.sql.SQLException;
import java.util.List;

public class LibrariansModel {

    private final UserDao dao;

    public LibrariansModel() throws SQLException {
        dao = new UserDaoImpl(DatabaseConnection.get().getConnection());
    }

    public List<User> getLibrarians() throws SQLException {
        return dao.getByRole(User.Role.LIBRARIAN);
    }

}
