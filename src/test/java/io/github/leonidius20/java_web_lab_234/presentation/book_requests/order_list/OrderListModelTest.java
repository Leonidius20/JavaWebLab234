package io.github.leonidius20.java_web_lab_234.presentation.book_requests.order_list;

import io.github.leonidius20.java_web_lab_234.dao.BookRequestDao;
import io.github.leonidius20.java_web_lab_234.domain.BookRequest;
import io.github.leonidius20.java_web_lab_234.presentation.book_requests.orders_list.OrdersListModelImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderListModelTest {


    @Test
    void getAllOrdersTest() throws SQLException {
        var mockDao = mock(BookRequestDao.class);

        var mockList = new LinkedList<BookRequest>();
        mockList.add(new BookRequest(0, 0, 0, "name", BookRequest.BorrowingType.READ_AT_LIBRARY, LocalDate.of(1990, 4, 5),  LocalDate.of(1990, 4, 5), BookRequest.Status.PENDING, ""));


        when(mockDao.getAll()).thenReturn(mockList);

        var mockModel = new OrdersListModelImpl(mockDao);

        Assertions.assertEquals(mockList, mockModel.getAllOrders());
    }

}


