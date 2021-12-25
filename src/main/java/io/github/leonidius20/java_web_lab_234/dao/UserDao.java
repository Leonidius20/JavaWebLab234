package io.github.leonidius20.java_web_lab_234.dao;

import io.github.leonidius20.java_web_lab_234.domain.User;

import java.sql.SQLException;

public interface UserDao {

    int insertUser(User user) throws SQLException;

    User getByName(String name) throws SQLException;

}
