package io.github.leonidius20.java_web_lab_234.dao;

import io.github.leonidius20.java_web_lab_234.domain.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    private static final String SELECT_ROLE = "select role_id from user_roles where upper(role_name) = ?";
    private static final String INSERT_USER = "insert into users(name, passport_number, role, password_hash, salt) values(?, ?, ?, ?, ?)";
    private static final String GET_USER_BY_NAME = "select * from users join user_roles on users.role = user_roles.role_id where name = ?";

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Inserts a user into DB.
     * @param user a user to insert (all fields including password hash and salt
     *             excluding id should be set)
     * @return id of the inserted user or -1 if failed
     * @throws SQLException if something goes wrong with db operations
     */
    @Override
    public int insertUser(User user) throws SQLException {
        int roleId = getRoleId(user.role());

        var statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.name());
        statement.setString(2, user.passportNumber());
        statement.setInt(3, roleId);
        statement.setBytes(4, user.passwordHash());
        statement.setBytes(5, user.salt());

        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) return -1;

        int id = -1;
        var generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) id = generatedKeys.getInt(1);
        return id;
    }

    @Override
    public User getByName(String name) throws SQLException {
        var statement = connection.prepareStatement(GET_USER_BY_NAME);
        statement.setString(1, name);
        var results = statement.executeQuery();

        if (!results.next()) return null;

        return new User(
                results.getInt("id"),
                results.getString("name"),
                results.getString("passport_number"),
                User.Role.valueOf(results.getString("role_name").toUpperCase()),
                results.getBoolean("is_banned"),
                results.getBytes("password_hash"),
                results.getBytes("salt")
        );
    }

    private int getRoleId(User.Role role) throws SQLException {
        var selectRole = connection.prepareStatement(SELECT_ROLE);
        selectRole.setString(1, role.name().toUpperCase());
        var resultSet = selectRole.executeQuery();
        if (!resultSet.next()) throw new IllegalArgumentException("Unknown role string");
        return resultSet.getInt("role_id");
    }

    public void banUser(int id) {

    }

    public void unbanUser(int id) {

    }

}
