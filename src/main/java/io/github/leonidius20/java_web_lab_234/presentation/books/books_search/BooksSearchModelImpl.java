package io.github.leonidius20.java_web_lab_234.presentation.books.books_search;

import io.github.leonidius20.java_web_lab_234.dao.BookDao;
import io.github.leonidius20.java_web_lab_234.dao.BookDaoImpl;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;
import io.github.leonidius20.java_web_lab_234.domain.Book;

import java.sql.SQLException;
import java.util.List;

public class BooksSearchModelImpl {

    private final BookDao dao;

    public BooksSearchModelImpl() throws SQLException {
        this.dao = new BookDaoImpl(DatabaseConnection.get().getConnection());
    }

    public BooksSearchModelImpl(BookDao dao) { this.dao = dao; }

    public List<Book> getAll(String orderByString) throws SQLException {
        var orderBy = orderByFromString(orderByString);
        return dao.findAll(orderBy);
    }

    public List<Book> getByQuery(String query, String searchByStr, String orderByStr) throws SQLException {
        var orderBy = orderByFromString(orderByStr);
        var searchBy = findByFromString(searchByStr);
        return dao.findByQuery(query, searchBy, orderBy);
    }

    private BookDao.OrderBy orderByFromString(String str) {
        return BookDao.OrderBy.valueOf(str.toUpperCase());
    }

    private BookDao.FindBy findByFromString(String str) {
        return BookDao.FindBy.valueOf(str.toUpperCase());
    }

}
