package io.github.leonidius20.java_web_lab_234.dao;

import io.github.leonidius20.java_web_lab_234.domain.BookRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class BookRequestDaoImpl extends BaseDao<BookRequest> implements BookRequestDao {

    private static final String INSERT =
            "insert into book_requests(user_id, book, borrowing_type, desired_date, end_date, status) values(?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ALL = "select * from book_requests join books on book_requests.book = books.id ";

    private static final String SELECT_ALL_WITH_USERNAME =
            "select request_id, user_id, book, borrowing_type, desired_date, end_date, status, books.name, users.name as \"username\" from" +
                    " book_requests join books on book_requests.book = books.id " +
                    " join users on users.id = book_requests.user_id";

    private static final String SELECT_FOR_USER = SELECT_ALL +
            " where user_id = ?";

    private static final String EXISTS = "select exists(select 1 from book_requests where user_id = ? and book = ?) as \"exists\"";

    private static final String DELETE = "delete from book_requests where request_id = ?";

    private static final String UPDATE_STATUS = "update book_requests set status = ? where request_id = ?";

    public BookRequestDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public List<BookRequest> getRequestsForUser(int userId) throws SQLException {
        var statement = connection.prepareStatement(SELECT_FOR_USER);
        statement.setInt(1, userId);

        var resultSet = statement.executeQuery();
        return requestsFromResultSet(resultSet);
    }

    public int createRequest(BookRequest request) throws SQLException {
        var statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, request.userId());
        statement.setInt(2, request.bookId());
        statement.setInt(3, request.borrowingType().getId());
        statement.setObject(4, request.desiredDate());
        statement.setObject(5, request.endDate());
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

    @Override
    public void deleteRequest(int requestId) throws SQLException {
        var statement = connection.prepareStatement(DELETE);
        statement.setInt(1, requestId);
        statement.executeUpdate();
    }

    private List<BookRequest> requestsFromResultSet(ResultSet resultSet) throws SQLException {
        List<BookRequest> list = new LinkedList<>();

        while (resultSet.next()) {
            var request = requestFromResultSet(resultSet);
            list.add(request);
        }

        return list;
    }

    private BookRequest requestFromResultSet(ResultSet resultSet) throws SQLException {
        var userName =  resultSet.getString("username");
        return new BookRequest(
                resultSet.getInt("request_id"),
                resultSet.getInt("user_id"),
                resultSet.getInt("book"),
                resultSet.getString("name"),
                BookRequest.BorrowingType.fromId(resultSet.getInt("borrowing_type")),
                resultSet.getObject("desired_date", LocalDate.class),
                resultSet.getObject("end_date", LocalDate.class),
                BookRequest.Status.fromId(resultSet.getInt("status")),
                userName == null ? "null" : userName
        );
    }

    @Override
    public List<BookRequest> getAll() throws SQLException {
        var statement = connection.prepareStatement(SELECT_ALL_WITH_USERNAME);
        var resultSet = statement.executeQuery();
        return requestsFromResultSet(resultSet);
    }

    @Override
    public void setRequestStatus(int requestId, BookRequest.Status status) throws SQLException {
        var statement = connection.prepareStatement(UPDATE_STATUS);
        statement.setInt(1, status.getId());
        statement.setInt(2, requestId);
        statement.executeUpdate();
    }

}
