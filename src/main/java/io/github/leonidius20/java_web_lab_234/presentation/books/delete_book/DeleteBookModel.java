package io.github.leonidius20.java_web_lab_234.presentation.books.delete_book;

import io.github.leonidius20.java_web_lab_234.dao.BookDao;
import io.github.leonidius20.java_web_lab_234.dao.BookDaoImpl;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;

import java.sql.SQLException;

public class DeleteBookModel {

    private final BookDao dao;

    public DeleteBookModel() throws SQLException {
        this.dao = new BookDaoImpl(DatabaseConnection.get().getConnection());
    }

    public DeleteBookModel(BookDao dao) {
        this.dao = dao;
    }

    void deleteById(int id) throws SQLException {
        dao.deleteById(id);
    }

}
