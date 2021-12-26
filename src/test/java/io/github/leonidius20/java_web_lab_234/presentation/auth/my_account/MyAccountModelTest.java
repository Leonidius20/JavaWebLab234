package io.github.leonidius20.java_web_lab_234.presentation.auth.my_account;

import io.github.leonidius20.java_web_lab_234.dao.BookRequestDao;
import io.github.leonidius20.java_web_lab_234.domain.BookRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MyAccountModelTest {

    @Test
    void getBookRequestsTest() throws SQLException {
        var mockDao = mock(BookRequestDao.class);

        var mockList = new LinkedList<BookRequest>();
        mockList.add(new BookRequest(0, 0, 0, "name", BookRequest.BorrowingType.READ_AT_LIBRARY, LocalDate.of(1990, 4, 5),  LocalDate.of(1990, 4, 5), BookRequest.Status.PENDING, ""));


        when(mockDao.getRequestsForUser(0)).thenReturn(mockList);

        var mockModel = new MyAccountModelImpl(mockDao);

        Assertions.assertEquals(mockList, mockModel.getBookRequests(0));
    }

}
