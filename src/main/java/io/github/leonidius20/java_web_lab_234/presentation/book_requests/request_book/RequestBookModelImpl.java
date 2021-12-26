package io.github.leonidius20.java_web_lab_234.presentation.book_requests.request_book;

import io.github.leonidius20.java_web_lab_234.dao.BookDao;
import io.github.leonidius20.java_web_lab_234.dao.BookDaoImpl;
import io.github.leonidius20.java_web_lab_234.dao.BookRequestDao;
import io.github.leonidius20.java_web_lab_234.dao.BookRequestDaoImpl;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;
import io.github.leonidius20.java_web_lab_234.domain.BookRequest;

import java.sql.SQLException;
import java.time.LocalDate;

public class RequestBookModelImpl {

    private final BookRequestDao dao;
    private final BookDao bookDao;

    public RequestBookModelImpl() throws SQLException {
        var connection = DatabaseConnection.get().getConnection();
        dao = new BookRequestDaoImpl(connection);
        bookDao = new BookDaoImpl(connection);
    }

    public RequestBookModelImpl(BookRequestDao dao, BookDao bookDao) {
        this.dao = dao;
        this.bookDao = bookDao;
    }

    public boolean requestExists(int userId, int bookId) throws SQLException {
        return dao.requestExists(userId, bookId);
    }

    public void requestBook(int userId, int bookId, String borrowingTypeString, String desiredDateStr, String endDateStr) throws SQLException {
        var borrowingType = BookRequest.BorrowingType.valueOf(borrowingTypeString.toUpperCase());

        var desiredDate = LocalDate.parse(desiredDateStr);
        var endDate = LocalDate.parse(endDateStr);

        dao.createRequest(new BookRequest(
                -1, userId, bookId, "", borrowingType, desiredDate, endDate, BookRequest.Status.PENDING, ""
        ));
    }

    public boolean hasAvailableCopies(int bookId) throws SQLException {
        return bookDao.findById(bookId).numberOfAvailableCopies() > 0;
    }

}
