package io.github.leonidius20.java_web_lab_234.presentation.books.delete_book;

import io.github.leonidius20.java_web_lab_234.dao.BookDao;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeleteBookTest {

    @Test
    void deleteBookTest() throws SQLException {
        var mockDao = mock(BookDao.class);
        var model = new DeleteBookModel(mockDao);
        model.deleteById(1);

        verify(mockDao).deleteById(1);
    }

}
