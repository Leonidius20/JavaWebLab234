package io.github.leonidius20.java_web_lab_234.presentation.book_details;

import io.github.leonidius20.java_web_lab_234.dao.BookDaoImpl;
import io.github.leonidius20.java_web_lab_234.dao.BookRequestDaoImpl;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;
import io.github.leonidius20.java_web_lab_234.domain.Book;

import javax.sql.DataSource;
import java.sql.SQLException;

public class BookDetailsModelImpl {

    private final DataSource connection = DatabaseConnection.get();

    public Book getById(int id) throws SQLException {
        BookDaoImpl dao = new BookDaoImpl(connection.getConnection());
        return dao.findById(id);
    }

    public boolean checkIfBookRequested(int userId, int bookId) throws SQLException {
        var dao = new BookRequestDaoImpl(connection.getConnection());
        return dao.requestExists(userId, bookId);
    }

}
