package io.github.leonidius20.java_web_lab_234.presentation.book_details;

import io.github.leonidius20.java_web_lab_234.dao.BookDao;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;
import io.github.leonidius20.java_web_lab_234.domain.Book;

import javax.sql.DataSource;
import java.sql.SQLException;

public class BookDetailsModelImpl {

    private final DataSource connection = DatabaseConnection.get();

    public Book getById(int id) throws SQLException {
        BookDao dao = new BookDao(connection.getConnection());
        return dao.findById(id);
    }

}
