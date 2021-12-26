package io.github.leonidius20.java_web_lab_234.presentation.request_book;

import io.github.leonidius20.java_web_lab_234.dao.BookRequestDao;
import io.github.leonidius20.java_web_lab_234.domain.BookRequest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

}
