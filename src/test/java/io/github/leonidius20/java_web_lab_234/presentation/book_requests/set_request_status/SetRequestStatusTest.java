package io.github.leonidius20.java_web_lab_234.presentation.book_requests.set_request_status;

import io.github.leonidius20.java_web_lab_234.dao.BookRequestDao;
import io.github.leonidius20.java_web_lab_234.domain.BookRequest;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SetRequestStatusTest {

    BookRequestDao mockDao = mock(BookRequestDao.class);
    SetRequestStatusModel model = new SetRequestStatusModel(mockDao);

    @Test
    void deleteRequestTest() throws SQLException {
        model.deleteRequest(1);

        verify(mockDao).deleteRequest(1);
    }

    @Test
    void setRequestStatusTest() throws SQLException {
        model.setRequestStatus(1, "taken");
        verify(mockDao).setRequestStatus(1, BookRequest.Status.TAKEN);
    }

}
