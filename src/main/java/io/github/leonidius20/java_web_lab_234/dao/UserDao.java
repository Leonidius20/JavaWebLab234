package io.github.leonidius20.java_web_lab_234.dao;

import io.github.leonidius20.java_web_lab_234.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    int insertUser(User user) throws SQLException;

    User getByName(String name) throws SQLException;

    List<User> getByRole(User.Role role) throws SQLException;

    void setUserBanned(int id, boolean banned) throws SQLException;

    void deleteUser(int id) throws SQLException;

}
