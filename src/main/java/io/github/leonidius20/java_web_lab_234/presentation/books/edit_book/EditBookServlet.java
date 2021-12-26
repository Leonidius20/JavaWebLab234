package io.github.leonidius20.java_web_lab_234.presentation.books.edit_book;

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

@WebServlet(name = "edit_book_servlet", value = "/edit-book")
public class EditBookServlet extends HttpServlet {

    private EditBookModelImpl model;

    @Override
    public void init() throws ServletException {
        try {
            model = new EditBookModelImpl();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("EditBookServlet: Could not initialize model");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                var book = model.loadBook(id);
                if (book == null) throw new IllegalArgumentException();
                req.setAttribute("book", book);
            } catch (SQLException e) {
                e.printStackTrace();
                ErrorPage.show("Unknown error", 500, req, resp, getServletContext());
                return;
            } catch (NumberFormatException e) {
                ErrorPage.show("Wrong ID format", 400, req, resp, getServletContext());
                return;
            } catch (IllegalArgumentException e) {
                ErrorPage.show("No book with such ID", 400, req, resp, getServletContext());
                return;
            }
        }
        getServletContext().getRequestDispatcher("/jsp/edit_book.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userName = ((User)req.getSession().getAttribute("user")).name();

        var id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        var year = Integer.parseInt(req.getParameter("year"));
        var edition = Integer.parseInt(req.getParameter("edition"));
        var numberOfCopies = Integer.parseInt(req.getParameter("number_of_copies"));

        try {
            if (id == -1) {
                id = model.createBook(name, author, year, edition, numberOfCopies);
                if (id == -1) {
                    throw new RuntimeException();
                }
                Logger.getLogger("EditBook").log(Level.INFO, userName + " has created a book with id " + id + " (" + name + ")");
            } else {
                model.editBook(id, name, author, year, edition, numberOfCopies);
                Logger.getLogger("EditBook").log(Level.INFO, userName + " has modified a book with id " + id + " (" + name + ")");
            }
            resp.sendRedirect(getServletContext().getContextPath() + "/book?id=" + id);
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            ErrorPage.show("Failed to perform the operation", 500, req, resp, getServletContext());
        }
    }

}
