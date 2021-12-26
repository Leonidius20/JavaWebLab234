package io.github.leonidius20.java_web_lab_234.presentation.users.ban_user;

import io.github.leonidius20.java_web_lab_234.dao.UserDao;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BanUserTest {

    @Test
    void banUserTest() throws SQLException {
        var mockDao = mock(UserDao.class);
        var model = new BanUserModel(mockDao);
        model.banUser(1);

        verify(mockDao).setUserBanned(1, true);
    }

}
