package io.github.leonidius20.java_web_lab_234.dao;

import io.github.leonidius20.java_web_lab_234.domain.BookRequest;

import java.sql.SQLException;
import java.util.List;

public interface BookRequestDao {

    public List<BookRequest> getRequestsForUser(int userId) throws SQLException;

    public int createRequest(BookRequest request) throws SQLException;

    public boolean requestExists(int userId, int bookId) throws SQLException;

    public void closeRequest(int requestId) throws SQLException;

}
