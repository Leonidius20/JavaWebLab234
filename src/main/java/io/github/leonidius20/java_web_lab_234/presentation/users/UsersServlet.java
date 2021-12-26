package io.github.leonidius20.java_web_lab_234.presentation.users;

import io.github.leonidius20.java_web_lab_234.domain.User;
import io.github.leonidius20.java_web_lab_234.utils.ErrorPage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "users_servlet", value = "/users")
public class UsersServlet extends HttpServlet {

    private UsersModel model;

    @Override
    public void init() throws ServletException {
        try {
            model = new UsersModel();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("UsersServlet: Could not create model");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var users = model.getUsers();
            req.setAttribute("users", users);
            req.setAttribute("role", User.Role.USER);
            getServletContext().getRequestDispatcher("/jsp/user-list.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            ErrorPage.show("Error loading users", 500, req, resp, getServletContext());
        }
    }

}
