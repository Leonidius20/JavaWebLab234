package io.github.leonidius20.java_web_lab_234.presentation.request_book;

import io.github.leonidius20.java_web_lab_234.domain.User;
import io.github.leonidius20.java_web_lab_234.utils.ErrorPage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "request_book_servlet", value = "/request-book")
public class RequestBookServlet extends HttpServlet {

    private RequestBookModelImpl model;

    @Override
    public void init() throws ServletException {
        try {
            model = new RequestBookModelImpl();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("RequestBookServlet: Could not create model");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/jsp/book_request.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = (User)req.getSession().getAttribute("user");

        try {
            var bookId = Integer.parseInt(req.getParameter("id"));

            if (model.requestExists(user.id(), bookId)) {
                ErrorPage.show("You have already requested this book", 400, req, resp, getServletContext());
                return;
            }

            var borrowingTypeString = req.getParameter("borrowing_type");
            var desiredDate = req.getParameter("desired_date");
            var endDate = req.getParameter("end_date");

            model.requestBook(user.id(), bookId, borrowingTypeString, desiredDate, endDate);

            Logger.getLogger("Book Requests").log(Level.INFO, user.name() + " has requested a book with id " + bookId);

            resp.sendRedirect(getServletContext().getContextPath() + "/account");
        } catch (Exception e) {
            e.printStackTrace();
            ErrorPage.show("Error", 500, req, resp, getServletContext());
        }
    }

}
