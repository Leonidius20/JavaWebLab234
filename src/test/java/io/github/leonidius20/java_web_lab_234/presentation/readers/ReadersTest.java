package io.github.leonidius20.java_web_lab_234.presentation.readers;

import io.github.leonidius20.java_web_lab_234.dao.UserWithRequestsDaoImpl;
import io.github.leonidius20.java_web_lab_234.domain.BookRequest;
import io.github.leonidius20.java_web_lab_234.domain.User;
import io.github.leonidius20.java_web_lab_234.domain.UserWithRequests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReadersTest {

    @Test
    void getUsersTest() throws SQLException {
        var mockDao = mock(UserWithRequestsDaoImpl.class);
        var model = new ReadersModel(mockDao);

        var list = List.of(
                new UserWithRequests(
                        new User(0, "", "", User.Role.LIBRARIAN, false, null, null),
                        List.of(new BookRequest(0, 0, 0, "", BookRequest.BorrowingType.TAKE_HOME, null, null, BookRequest.Status.PENDING, "")))
        );

        when(mockDao.getUsersWithRequests()).thenReturn(list);

        Assertions.assertEquals(list, model.getUsers());
    }

}
