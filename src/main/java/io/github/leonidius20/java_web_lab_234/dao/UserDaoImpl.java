package io.github.leonidius20.java_web_lab_234.dao;

import io.github.leonidius20.java_web_lab_234.domain.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    private static final String SELECT_ROLE = "select role_id from user_roles where upper(role_name) = ?";
    private static final String INSERT_USER = "insert into users(name, passport_number, role, password_hash, salt) values(?, ?, ?, ?, ?)";
    private static final String GET_USER_BY_NAME = "select * from users join user_roles on users.role = user_roles.role_id where name = ?";
    private static final String GET_USERS_BY_ROLE = "select * from users join user_roles on users.role = user_roles.role_id where upper(role_name) = upper(?)";
    private static final String DELETE_BY_ID = "delete from users where id = ?";
    private static final String UPDATE_BANNED = "update users set is_banned = ? where id = ?";

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

        return userFromResultSet(results);
    }

    private int getRoleId(User.Role role) throws SQLException {
        var selectRole = connection.prepareStatement(SELECT_ROLE);
        selectRole.setString(1, role.name().toUpperCase());
        var resultSet = selectRole.executeQuery();
        if (!resultSet.next()) throw new IllegalArgumentException("Unknown role string");
        return resultSet.getInt("role_id");
    }

    @Override
    public List<User> getByRole(User.Role role) throws SQLException {
        var statement = connection.prepareStatement(GET_USERS_BY_ROLE);
        statement.setString(1, role.toString());
        ResultSet resultSet = statement.executeQuery();

        return usersFromResultSet(resultSet);
    }

    private List<User> usersFromResultSet(ResultSet resultSet) throws SQLException {
        List<User> list = new LinkedList<>();

        while (resultSet.next()) {
            list.add(userFromResultSet(resultSet));
        }

        return list;
    }

    private User userFromResultSet(ResultSet results) throws SQLException {
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

    @Override
    public void setUserBanned(int id, boolean banned) throws SQLException {
        var statement = connection.prepareStatement(UPDATE_BANNED);
        statement.setBoolean(1, banned);
        statement.setInt(2, id);
        statement.executeUpdate();
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        var statement = connection.prepareStatement(DELETE_BY_ID);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

}
