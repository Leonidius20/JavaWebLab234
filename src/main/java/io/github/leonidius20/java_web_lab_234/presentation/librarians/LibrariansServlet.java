package io.github.leonidius20.java_web_lab_234.presentation.librarians;

import io.github.leonidius20.java_web_lab_234.domain.User;
import io.github.leonidius20.java_web_lab_234.utils.ErrorPage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "librarians_servlet", value = "/librarians")
public class LibrariansServlet extends HttpServlet {

    private LibrariansModel model;

    @Override
    public void init() throws ServletException {
        try {
            model = new LibrariansModel();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("LibrariansServlet: Could not create model");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var librarians = model.getLibrarians();
            req.setAttribute("users", librarians);
            req.setAttribute("role", User.Role.LIBRARIAN);
            getServletContext().getRequestDispatcher("/jsp/user-list.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            ErrorPage.show("Error loading librarians", 500, req, resp, getServletContext());
        }


    }


}
