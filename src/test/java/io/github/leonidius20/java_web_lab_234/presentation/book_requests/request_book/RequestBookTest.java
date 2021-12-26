package io.github.leonidius20.java_web_lab_234.presentation.book_requests.request_book;

import io.github.leonidius20.java_web_lab_234.dao.BookDao;
import io.github.leonidius20.java_web_lab_234.dao.BookRequestDao;
import io.github.leonidius20.java_web_lab_234.domain.Book;
import io.github.leonidius20.java_web_lab_234.domain.BookRequest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RequestBookTest {

    @Test
    public void requestBookTest() throws SQLException {
        var mockDao = mock(BookRequestDao.class);

        var userId = 1;
        var bookId = 1;
        var borrowingString = "read_at_library";
        var borrowing = BookRequest.BorrowingType.READ_AT_LIBRARY;
        var desiredDateStr = "2002-09-28";
        var endDateStr = "2002-09-29";

        var desiredDate = LocalDate.of(2002, 9, 28);
        var endDate = LocalDate.of(2002, 9, 29);

        new RequestBookModelImpl(mockDao, null).requestBook(userId, bookId, borrowingString, desiredDateStr, endDateStr);

        var captor = ArgumentCaptor.forClass(BookRequest.class);
        verify(mockDao).createRequest(captor.capture());


        var actualRequest = captor.getValue();

        var expectedRequest = new BookRequest(
                -1, userId, bookId, "", borrowing, desiredDate, endDate, BookRequest.Status.PENDING, ""
        );

        assertEquals(expectedRequest, actualRequest);
    }

    @Test
    void hasAvailableBooksTest() throws SQLException {
        var mockDao = mock(BookDao.class);

        var mockBook0 = new Book(0, "", 0, 1, "", 0, 0);

        var mockBook1 = new Book(0, "", 0, 2, "", 0, 1);

        when(mockDao.findById(1)).thenReturn(mockBook0);
        when(mockDao.findById(2)).thenReturn(mockBook1);

        var mockModel =  new RequestBookModelImpl(null, mockDao);

        assertFalse(mockModel.hasAvailableCopies(1));
        assertTrue(mockModel.hasAvailableCopies(2));
    }

}
