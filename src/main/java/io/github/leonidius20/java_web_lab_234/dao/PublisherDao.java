package io.github.leonidius20.java_web_lab_234.dao;

import io.github.leonidius20.java_web_lab_234.domain.Publisher;

import java.sql.Connection;
import java.sql.SQLException;

public class PublisherDao extends BaseDao<Publisher> {

    private static final String SELECT_BY_ID = "SELECT * FROM \"Publishers\" where id = ?";

    public PublisherDao(Connection connection) {
        this.connection = connection;
    }

    public Publisher getById(int id) throws SQLException {
        var statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        var resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new Publisher(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            );
        } else return null;
    }

}
