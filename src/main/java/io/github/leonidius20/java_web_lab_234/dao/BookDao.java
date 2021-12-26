package io.github.leonidius20.java_web_lab_234.dao;

import io.github.leonidius20.java_web_lab_234.domain.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class BookDao extends BaseDao<Book> {

    private static final String SELECT_ALL =
            "select * from books";

    private static final String SELECT_BY_ID =
            SELECT_ALL + " where id = ?";

    private static final String DELETE_BY_ID = "delete from books where id = ?";

    private static final String UPDATE =
            "update books set name = ?, author_name = ?, year = ?, edition = ?, num_of_copies = ? where id = ?";

    private static final String INSERT =
            "insert into books (name, author_name, year, edition, num_of_copies) values (?, ?, ?, ?, ?)";

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
                    resultSet.getInt("year"),
                    resultSet.getInt("num_of_copies"),
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
                    resultSet.getInt("year"),
                    resultSet.getInt("num_of_copies"),
                    resultSet.getString("author_name"),
                    resultSet.getInt("edition")
            );
        } else return null;
    }

    public void deleteById(int id) throws SQLException {
        var statement = connection.prepareStatement(DELETE_BY_ID);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public boolean updateBook(Book book) throws SQLException {
        var statement = connection.prepareStatement(UPDATE);
        statement.setString(1, book.name());
        statement.setString(2, book.authorName());
        statement.setInt(3, book.year());
        statement.setInt(4, book.edition());
        statement.setInt(5, book.numberOfCopies());
        statement.setInt(6, book.id());

        int affectedRows = statement.executeUpdate();
        return affectedRows != 0;
    }

    public int createBook(Book book) throws SQLException {
        var statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, book.name());
        statement.setString(2, book.authorName());
        statement.setInt(3, book.year());
        statement.setInt(4, book.edition());
        statement.setInt(5, book.numberOfCopies());

        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) return -1;


        int id = -1;
        var generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) id = generatedKeys.getInt(1);
        return id;
    }


    public enum OrderBy  {

        NAME(" order by name"),
        AUTHOR(" order by author_name"),
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
