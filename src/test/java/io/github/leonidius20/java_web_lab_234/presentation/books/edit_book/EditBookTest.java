package io.github.leonidius20.java_web_lab_234.presentation.books.edit_book;

import io.github.leonidius20.java_web_lab_234.dao.BookDao;
import io.github.leonidius20.java_web_lab_234.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class EditBookTest {

    BookDao mockDao = mock(BookDao.class);
    EditBookModelImpl model = new EditBookModelImpl(mockDao);

    @Test
    void loadBookTest() throws SQLException {
        var book = new Book(0, "", 0, 1, "", 0, 0);

        when(mockDao.findById(1)).thenReturn(book);

        Assertions.assertEquals(book, model.loadBook(1));
    }

    @Test
    void createBook() throws SQLException {
        var book = new Book(-1, "", 0, 1, "", 0, -1);
        model.createBook(book.name(), book.authorName(), book.year(), book.edition(), book.numberOfCopies());
        verify(mockDao).createBook(book);
    }

    @Test
    void editBook() throws SQLException {
        var book = new Book(1, "", 0, 1, "", 0, -1);
        model.editBook(book.id(), book.name(), book.authorName(), book.year(), book.edition(), book.numberOfCopies());
        verify(mockDao).updateBook(book);
    }

}
