package io.github.leonidius20.java_web_lab_234.dao;

import io.github.leonidius20.java_web_lab_234.domain.Book;
import io.github.leonidius20.java_web_lab_234.domain.Publisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class BookDao extends BaseDao<Book> {

    private static final String SELECT_ALL =
            "select * from books join publishers on books.publisher = publishers.publisher_id join book_authors on books.author = book_authors.author_id";

    private static final String SELECT_BY_ID =
            SELECT_ALL + " where id = ?";

    public BookDao(Connection connection) {
        this.connection = connection;
    }

    // @Override
    public List<Book> findAll(OrderBy orderBy) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL + orderBy.getSqlOrderBy());
        List<Book> list = new LinkedList<>();

        while (resultSet.next()) {
            var book = new Book(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("author"),
                    resultSet.getInt("publisher"),
                    resultSet.getInt("year"),
                    resultSet.getInt("num_of_copies"),
                    resultSet.getString("publisher_name"),
                    resultSet.getString("author_name"),
                    resultSet.getInt("edition")
            );
            list.add(book);
        }

        return list;
    }

    public Book findById(int id) throws SQLException {
        var statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        var resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new Book(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("author"),
                    resultSet.getInt("publisher"),
                    resultSet.getInt("year"),
                    resultSet.getInt("num_of_copies"),
                    resultSet.getString("publisher_name"),
                    resultSet.getString("author_name"),
                    resultSet.getInt("edition")
            );
        } else return null;
    }

    public enum OrderBy  {

        NAME(" order by name"),
        AUTHOR(" order by author_name"),
        PUBLISHER(" order by publisher_name"),
        EDITION(" order by edition"),
        YEAR(" order by year");

        private final String sqlOrderBy;

        OrderBy(String sqlOrderBy) {
            this.sqlOrderBy = sqlOrderBy;
        }

        public String getSqlOrderBy() {
            return sqlOrderBy;
        }
    }

}
