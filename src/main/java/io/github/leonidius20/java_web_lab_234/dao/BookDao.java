package io.github.leonidius20.java_web_lab_234.dao;

import io.github.leonidius20.java_web_lab_234.domain.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {

    List<Book> findAll(BookDaoImpl.OrderBy orderBy) throws SQLException;

    List<Book> findByQuery(String query, BookDaoImpl.FindBy findBy, BookDaoImpl.OrderBy orderBy) throws SQLException;

    Book findById(int id) throws SQLException;

    void deleteById(int id) throws SQLException;

    boolean updateBook(Book book) throws SQLException;

    int createBook(Book book) throws SQLException;

}
