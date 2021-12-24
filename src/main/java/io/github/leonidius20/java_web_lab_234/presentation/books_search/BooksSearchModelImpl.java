package io.github.leonidius20.java_web_lab_234.presentation.books_search;

import io.github.leonidius20.java_web_lab_234.dao.PublisherDao;
import io.github.leonidius20.java_web_lab_234.domain.Book;
import io.github.leonidius20.java_web_lab_234.dao.BookDao;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class BooksSearchModelImpl {

    private final DataSource connection = DatabaseConnection.get();

    public List<Book> getAll() throws SQLException {
        BookDao dao = new BookDao(connection.getConnection());
        return dao.findAll();
    }

    public void close() {

    }

}
