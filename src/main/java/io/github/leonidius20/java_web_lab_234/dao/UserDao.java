package io.github.leonidius20.java_web_lab_234.dao;

import io.github.leonidius20.java_web_lab_234.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    int insertUser(User user) throws SQLException;

    User getByName(String name) throws SQLException;

    List<User> getByRole(User.Role role) throws SQLException;

    void banUser(int id) throws SQLException;

    void unbanUser(int id) throws SQLException;

    void deleteUser(int id) throws SQLException;

}
