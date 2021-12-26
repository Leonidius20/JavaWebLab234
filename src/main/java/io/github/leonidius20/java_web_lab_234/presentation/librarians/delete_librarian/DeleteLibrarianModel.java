package io.github.leonidius20.java_web_lab_234.presentation.librarians.delete_librarian;

import io.github.leonidius20.java_web_lab_234.dao.UserDao;
import io.github.leonidius20.java_web_lab_234.dao.UserDaoImpl;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;

import java.sql.SQLException;

public class DeleteLibrarianModel {

    private final UserDao dao;

    public DeleteLibrarianModel() throws SQLException {
        this.dao = new UserDaoImpl(DatabaseConnection.get().getConnection());
    }

    public DeleteLibrarianModel(UserDao dao) throws SQLException {
        this.dao = dao;
    }

    public void deleteLibrarian(int id) throws SQLException {
        dao.deleteUser(id);
    }

}
