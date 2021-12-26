package io.github.leonidius20.java_web_lab_234.presentation.books_search;

import io.github.leonidius20.java_web_lab_234.dao.BookDao;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;
import io.github.leonidius20.java_web_lab_234.domain.Book;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class BooksSearchModelImpl {

    private final DataSource connection = DatabaseConnection.get();

    public List<Book> getAll(String orderByString) throws SQLException {
        BookDao dao = new BookDao(connection.getConnection());
        var orderBy = BookDao.OrderBy.valueOf(orderByString.toUpperCase());
        return dao.findAll(orderBy);
    }

    public void close() {

    }

}
