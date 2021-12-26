package io.github.leonidius20.java_web_lab_234.presentation.users.users_list;

import io.github.leonidius20.java_web_lab_234.dao.UserDao;
import io.github.leonidius20.java_web_lab_234.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersTest {

    @Test
    void getUsersTest() throws SQLException {
        var mockDao = mock(UserDao.class);
        var model = new UsersModel(mockDao);

        var list = List.of(new User(0, "", "", User.Role.USER, false, null, null));

        when(mockDao.getByRole(User.Role.USER)).thenReturn(list);

        Assertions.assertEquals(list, model.getUsers());
    }

}
