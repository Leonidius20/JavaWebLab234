package io.github.leonidius20.java_web_lab_234.presentation.books.book_details;

import io.github.leonidius20.java_web_lab_234.dao.BookDao;
import io.github.leonidius20.java_web_lab_234.dao.BookDaoImpl;
import io.github.leonidius20.java_web_lab_234.dao.BookRequestDao;
import io.github.leonidius20.java_web_lab_234.dao.BookRequestDaoImpl;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;
import io.github.leonidius20.java_web_lab_234.domain.Book;

import java.sql.SQLException;

public class BookDetailsModelImpl {

    private final BookDao dao;
    private final BookRequestDao requestDao;

    public BookDetailsModelImpl() throws SQLException {
        var connection = DatabaseConnection.get().getConnection();
        dao = new BookDaoImpl(connection);
        requestDao = new BookRequestDaoImpl(connection);
    }

    public BookDetailsModelImpl(BookDao dao, BookRequestDao requestDao) {
        this.dao = dao;
        this.requestDao = requestDao;
    }

    public Book getById(int id) throws SQLException {
        return dao.findById(id);
    }

    public boolean checkIfBookRequested(int userId, int bookId) throws SQLException {
        return requestDao.requestExists(userId, bookId);
    }

}
