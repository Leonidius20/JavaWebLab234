package io.github.leonidius20.java_web_lab_234.presentation.auth.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "login_servlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private LoginModelImpl model;

    @Override
    public void init() throws ServletException {
        try {
            model = new LoginModelImpl();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("LoginServlet: Could not initialize model");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession(true).getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (name.isEmpty() || password.isEmpty()) {
            showErrorPage("All fields have to be filled", 400, req, resp);
            return;
        }

        try {
            var user = model.getUserWithCredentials(name, password);

            if (user == null) {
                showErrorPage("Wrong credentials", 403, req, resp);
                Logger.getLogger("Auth").log(Level.INFO, "Someone failed to log in as " + name);
            } else {
                if (user.isBanned()) {
                    showErrorPage("You are banned", 403, req, resp);
                    return;
                }

                req.getSession(true).setAttribute("user", user);
                resp.sendRedirect(req.getContextPath());
                Logger.getLogger("Auth").log(Level.INFO, "User logged in as " + name);
            }

        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            showErrorPage("Error when verifying credentials", 500, req, resp);
        }

    }

    private void showErrorPage(String message, int code, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(code);
        req.setAttribute("errorMessage", message);
        req.setAttribute("backLink", req.getPathInfo());
        getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
    }

}
