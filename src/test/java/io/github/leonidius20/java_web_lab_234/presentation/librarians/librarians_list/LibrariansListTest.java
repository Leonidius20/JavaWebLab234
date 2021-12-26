package io.github.leonidius20.java_web_lab_234.presentation.librarians.librarians_list;

import io.github.leonidius20.java_web_lab_234.dao.UserDao;
import io.github.leonidius20.java_web_lab_234.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibrariansListTest {

    @Test
    void getLibrariansTest() throws SQLException {
        var mockDao = mock(UserDao.class);
        var model = new LibrariansModel(mockDao);

        var list = List.of(new User(0, "", "", User.Role.LIBRARIAN, false, null, null));

        when(mockDao.getByRole(User.Role.LIBRARIAN)).thenReturn(list);

        Assertions.assertEquals(list, model.getLibrarians());
    }

}
