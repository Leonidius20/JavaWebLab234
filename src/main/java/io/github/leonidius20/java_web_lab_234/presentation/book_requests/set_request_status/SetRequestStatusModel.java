package io.github.leonidius20.java_web_lab_234.presentation.book_requests.set_request_status;

import io.github.leonidius20.java_web_lab_234.dao.BookRequestDao;
import io.github.leonidius20.java_web_lab_234.dao.BookRequestDaoImpl;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;
import io.github.leonidius20.java_web_lab_234.domain.BookRequest;

import java.sql.SQLException;

public class SetRequestStatusModel {

    private final BookRequestDao dao;

    public SetRequestStatusModel() throws SQLException {
        dao = new BookRequestDaoImpl(DatabaseConnection.get().getConnection());
    }

    public SetRequestStatusModel(BookRequestDao dao) {
        this.dao = dao;
    }

    public void setRequestStatus(int id, String statusString) throws SQLException {
        var status = BookRequest.Status.valueOf(statusString.toUpperCase());
        dao.setRequestStatus(id, status);
    }

    public void deleteRequest(int id) throws SQLException {
        dao.deleteRequest(id);
    }

}
