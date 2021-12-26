package io.github.leonidius20.java_web_lab_234.presentation.book_requests.orders_list;

import io.github.leonidius20.java_web_lab_234.dao.BookRequestDao;
import io.github.leonidius20.java_web_lab_234.dao.BookRequestDaoImpl;
import io.github.leonidius20.java_web_lab_234.data.DatabaseConnection;
import io.github.leonidius20.java_web_lab_234.domain.BookRequest;

import java.sql.SQLException;
import java.util.List;

public class OrdersListModelImpl {

    private final BookRequestDao dao;

    public OrdersListModelImpl() throws SQLException {
        dao = new BookRequestDaoImpl(DatabaseConnection.get().getConnection());
    }

    public List<BookRequest> getAllOrders() throws SQLException {
        return dao.getAll();
    }

}
