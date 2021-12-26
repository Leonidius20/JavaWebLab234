package io.github.leonidius20.java_web_lab_234.presentation.books.edit_book;

import io.github.leonidius20.java_web_lab_234.dao.BookDao;
import io.github.leonidius20.java_web_lab_234.dao.BookDaoImpl;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;
import io.github.leonidius20.java_web_lab_234.domain.Book;

import java.sql.SQLException;

public class EditBookModelImpl {

    private final BookDao dao;

    public EditBookModelImpl() throws SQLException {
        this.dao = new BookDaoImpl(DatabaseConnection.get().getConnection());
    }

    public EditBookModelImpl(BookDao dao) {
        this.dao = dao;
    }

    public boolean editBook(int id, String name, String author, int year, int edition, int numberOfCopies) throws SQLException {
        return dao.updateBook(new Book(id, name, year, numberOfCopies, author, edition, -1));
    }

    public int createBook(String name, String author, int year, int edition, int numberOfCopies) throws SQLException {
        return dao.createBook(new Book(-1, name, year, numberOfCopies, author, edition, -1));
    }

    public Book loadBook(int id) throws SQLException {
        return dao.findById(id);
    }

}
