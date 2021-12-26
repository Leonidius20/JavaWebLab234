package io.github.leonidius20.java_web_lab_234.presentation.auth.my_account;

import io.github.leonidius20.java_web_lab_234.dao.BookRequestDao;
import io.github.leonidius20.java_web_lab_234.dao.BookRequestDaoImpl;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;
import io.github.leonidius20.java_web_lab_234.domain.BookRequest;

import java.sql.SQLException;
import java.util.List;

public class MyAccountModelImpl {

    private final BookRequestDao dao;

    public MyAccountModelImpl() throws SQLException {
        dao = new BookRequestDaoImpl(DatabaseConnection.get().getConnection());
    }

    public MyAccountModelImpl(BookRequestDao dao) throws SQLException {
        this.dao = dao;
    }

    public List<BookRequest> getBookRequests(int userId) throws SQLException {
        return dao.getRequestsForUser(userId);
    }

}
