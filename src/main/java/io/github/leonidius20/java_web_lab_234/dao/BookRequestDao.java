package io.github.leonidius20.java_web_lab_234.dao;

import io.github.leonidius20.java_web_lab_234.domain.BookRequest;

import java.sql.SQLException;
import java.util.List;

public interface BookRequestDao {

    List<BookRequest> getAll() throws SQLException;

    List<BookRequest> getRequestsForUser(int userId) throws SQLException;

    int createRequest(BookRequest request) throws SQLException;

    boolean requestExists(int userId, int bookId) throws SQLException;

    void setRequestStatus(int requestId, BookRequest.Status status) throws SQLException;

    void deleteRequest(int requestId) throws SQLException;

}
