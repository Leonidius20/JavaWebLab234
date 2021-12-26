package io.github.leonidius20.java_web_lab_234.presentation.books_search;

import io.github.leonidius20.java_web_lab_234.dao.BookDao;
import io.github.leonidius20.java_web_lab_234.dao.BookDaoImpl;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;
import io.github.leonidius20.java_web_lab_234.domain.Book;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class BooksSearchModelImpl {

    private final BookDao dao;

    public BooksSearchModelImpl() throws SQLException {
        this.dao = new BookDaoImpl(DatabaseConnection.get().getConnection());
    }

    public BooksSearchModelImpl(BookDao dao) { this.dao = dao; }

    private final DataSource connection = DatabaseConnection.get();

    public List<Book> getAll(String orderByString) throws SQLException {
        var orderBy = BookDaoImpl.OrderBy.valueOf(orderByString.toUpperCase());
        return dao.findAll(orderBy);
    }

    public List<Book> getByQuery(String query, String searchByStr, String orderByStr) throws SQLException {
        var orderBy = BookDaoImpl.OrderBy.valueOf(orderByStr.toUpperCase());
        var searchBy = BookDaoImpl.FindBy.valueOf(searchByStr.toUpperCase());
        return dao.findByQuery(query, searchBy, orderBy);
    }

}
