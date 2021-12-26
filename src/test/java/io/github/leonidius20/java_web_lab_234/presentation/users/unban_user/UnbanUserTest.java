package io.github.leonidius20.java_web_lab_234.presentation.users.unban_user;

import io.github.leonidius20.java_web_lab_234.dao.UserDao;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UnbanUserTest {

    @Test
    void unbanUserTest() throws SQLException {
        var mockDao = mock(UserDao.class);
        var model = new UnbanUserModel(mockDao);
        model.unbanUser(1);

        verify(mockDao).setUserBanned(1, false);
    }

}
