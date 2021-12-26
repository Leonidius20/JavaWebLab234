package io.github.leonidius20.java_web_lab_234.presentation.librarians.delete_librarian;

import io.github.leonidius20.java_web_lab_234.dao.UserDao;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DeleteLibrarianTest {

    @Test
    void deleteLibrarianTest() throws SQLException {
        var mockDao = mock(UserDao.class);
        var model = new DeleteLibrarianModel(mockDao);
        model.deleteLibrarian(1);

        verify(mockDao).deleteUser(1);
    }


}
