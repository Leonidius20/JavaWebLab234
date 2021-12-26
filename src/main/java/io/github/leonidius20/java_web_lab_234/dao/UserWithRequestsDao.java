package io.github.leonidius20.java_web_lab_234.dao;

import io.github.leonidius20.java_web_lab_234.domain.UserWithRequests;

import java.sql.SQLException;
import java.util.List;

public interface UserWithRequestsDao {

    List<UserWithRequests> getUsersWithRequests() throws SQLException;

}
