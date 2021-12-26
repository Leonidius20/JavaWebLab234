package io.github.leonidius20.java_web_lab_234.presentation.books.book_details;

import io.github.leonidius20.java_web_lab_234.dao.BookDao;
import io.github.leonidius20.java_web_lab_234.dao.BookRequestDao;
import io.github.leonidius20.java_web_lab_234.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookDetailsTest {

    @Test
    void getByIdTest() throws SQLException {
        var mockDao = mock(BookDao.class);

        var book = new Book(0, "", 0, 1, "", 0, 0);

        when(mockDao.findById(1)).thenReturn(book);

        var mockModel = new BookDetailsModelImpl(mockDao, null);

        Assertions.assertEquals(book, mockModel.getById(1));
    }

    @Test
    void checkIfBookRequestedTest() throws SQLException {
        var mockDao = mock(BookRequestDao.class);
        when(mockDao.requestExists(0, 0)).thenReturn(false);
        var mockModel = new BookDetailsModelImpl(null, mockDao);
        Assertions.assertFalse(mockModel.checkIfBookRequested(0, 0));
    }

}
