package io.github.leonidius20.java_web_lab_234.presentation.book_details;

import io.github.leonidius20.java_web_lab_234.domain.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "book_details_servlet", value = "/book")
public class BookDetailsServlet extends HttpServlet {

    private BookDetailsModelImpl model;

    @Override
    public void init() throws ServletException {
        super.init();
        model = new BookDetailsModelImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            var book = model.getById(id);
            req.setAttribute("book", book);

            var user = req.getSession().getAttribute("user");
            if (user != null) {
                req.setAttribute("book_requested", model.checkIfBookRequested(((User)user).id(), id));
            }

            getServletContext().getRequestDispatcher("/jsp/book_details.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("/error.jsp");
        }
    }

}
