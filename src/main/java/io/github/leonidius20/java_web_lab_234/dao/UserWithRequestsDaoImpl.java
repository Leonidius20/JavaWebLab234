package io.github.leonidius20.java_web_lab_234.dao;

import io.github.leonidius20.java_web_lab_234.domain.User;
import io.github.leonidius20.java_web_lab_234.domain.UserWithRequests;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserWithRequestsDaoImpl extends BaseDao<UserWithRequests> {

    private final UserDao userDao;
    private final BookRequestDao bookRequestDao;

    public UserWithRequestsDaoImpl(Connection connection) {
        this.connection = connection;
        userDao = new UserDaoImpl(connection);
        bookRequestDao = new BookRequestDaoImpl(connection);
    }

    public List<UserWithRequests> getUsersWithRequests() throws SQLException {
        var users = userDao.getByRole(User.Role.USER);

        List<UserWithRequests> results = new LinkedList<>();

        for (var user: users) {
            var requests = bookRequestDao.getRequestsForUser(user.id());
            results.add(new UserWithRequests(user, requests));
        }

        return results;
    }

}
