package io.github.leonidius20.java_web_lab_234.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class BookDao extends BaseDao<Book> {

    private static final String SELECT_ALL = "select * from \"Books\"";

    public BookDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Book> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL);
        List<Book> list = new LinkedList<>();

        while (resultSet.next()) {
            var book = new Book(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("author"),
                    resultSet.getInt("publisher"),
                    resultSet.getInt("year"),
                    resultSet.getInt("num_of_copies")
            );
            list.add(book);
        }

        return list;
    }

}
