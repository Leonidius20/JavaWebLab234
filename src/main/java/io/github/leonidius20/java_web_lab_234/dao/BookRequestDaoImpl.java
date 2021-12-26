package io.github.leonidius20.java_web_lab_234.dao;

import io.github.leonidius20.java_web_lab_234.domain.BookRequest;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class BookRequestDaoImpl extends BaseDao<BookRequest> implements BookRequestDao {

    private static final String INSERT =
            "insert into book_requests(user_id, book, borrowing_type, desired_date, end_date, status) values(?, ?, ?, ?, ?, ?)";

    private static final String SELECT_FOR_USER =
            "select * from book_requests join books on book_requests.book = books.id where user_id = ?";

    private static final String EXISTS = "select exists(select 1 from book_requests where user_id = ? and book = ?) as \"exists\"";

    private static final String DELETE = "delete from book_requests where request_id = ?";

    public BookRequestDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public List<BookRequest> getRequestsForUser(int userId) throws SQLException {
        var statement = connection.prepareStatement(SELECT_FOR_USER);
        statement.setInt(1, userId);

        var resultSet = statement.executeQuery();
        List<BookRequest> list = new LinkedList<>();

        while (resultSet.next()) {
            var request = new BookRequest(
                    resultSet.getInt("request_id"),
                    resultSet.getInt("user_id"),
                    resultSet.getInt("book"),
                    resultSet.getString("name"),
                    BookRequest.BorrowingType.fromId(resultSet.getInt("borrowing_type")),
                    resultSet.getDate("desired_date"),
                    resultSet.getDate("end_date"),
                    BookRequest.Status.fromId(resultSet.getInt("status"))
            );
            list.add(request);
        }

        return list;
    }

    public int createRequest(BookRequest request) throws SQLException {
        var statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, request.userId());
        statement.setInt(2, request.bookId());
        statement.setInt(3, request.borrowingType().getId());
        statement.setDate(4, request.desiredDate());
        statement.setDate(5, request.endDate());
        statement.setInt(6, request.status().getId());

        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) return -1;

        int id = -1;
        var generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) id = generatedKeys.getInt(1);
        return id;
    }

    public boolean requestExists(int userId, int bookId) throws SQLException {
        var statement = connection.prepareStatement(EXISTS);
        statement.setInt(1, userId);
        statement.setInt(2, bookId);
        var resultSet = statement.executeQuery();
        return resultSet.next() && resultSet.getBoolean("exists");
    }

    public void closeRequest(int requestId) throws SQLException {
        var statement = connection.prepareStatement(DELETE);
        statement.setInt(1, requestId);
        statement.executeUpdate();
    }

}
