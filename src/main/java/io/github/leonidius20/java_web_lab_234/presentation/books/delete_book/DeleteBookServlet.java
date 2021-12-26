package io.github.leonidius20.java_web_lab_234.presentation.books.delete_book;

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

@WebServlet(name = "delete_book_servlet", value = "/delete-book")
public class DeleteBookServlet extends HttpServlet {

    private DeleteBookModel model;

    @Override
    public void init() throws ServletException {
        try {
            model = new DeleteBookModel();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("DeleteBookServlet: Could not create model");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userName = ((User)req.getSession().getAttribute("user")).name();

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            model.deleteById(id);
            Logger.getLogger("Books").log(Level.INFO, userName + " deleted a book with ID " + id);
            resp.sendRedirect(req.getContextPath());
        } catch (SQLException e) {
            e.printStackTrace();
            ErrorPage.show("Unknown error", 500, req, resp, getServletContext());
        } catch (NumberFormatException e) {
            ErrorPage.show("Wrong ID format", 400, req, resp, getServletContext());
        }
    }

}
