package io.github.leonidius20.java_web_lab_234.presentation.books_search;

import io.github.leonidius20.java_web_lab_234.dao.BookDao;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BooksSearchTest {

    private final BookDao mockedDao = mock(BookDao.class);
    private final BooksSearchModelImpl model = new BooksSearchModelImpl(mockedDao);

    @Test
    void getByQueryTest() throws SQLException {
        var orderByStr = "edition";
        var findByStr = "author_or_name";
        var query = "query";

        var orderBy = BookDao.OrderBy.EDITION;
        var findBy = BookDao.FindBy.AUTHOR_OR_NAME;

        model.getByQuery(query, findByStr, orderByStr);

        verify(mockedDao).findByQuery(query, findBy, orderBy);
    }

    @Test
    void getAllTest() throws SQLException {
        var orderByStr = "author";

        var orderBy = BookDao.OrderBy.AUTHOR;

        model.getAll(orderByStr);

        verify(mockedDao).findAll(orderBy);
    }

}
