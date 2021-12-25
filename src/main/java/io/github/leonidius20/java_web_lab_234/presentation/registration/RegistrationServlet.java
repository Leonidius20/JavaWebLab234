package io.github.leonidius20.java_web_lab_234.presentation.registration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "registration_servlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {

    private RegistrationModelImpl model;

    @Override
    public void init() throws ServletException {
        try {
            model = new RegistrationModelImpl();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("RegistrationServlet: Could not create model");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession(true).getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String passportNum = req.getParameter("passport");
        String password = req.getParameter("password");

        if (name.isEmpty() || passportNum.isEmpty() || password.isEmpty()) {
            resp.setStatus(400); // bad request
            showErrorPage("All fields have to be filled", req, resp);
            return;
        }

        try {
            var user = model.createUser(name, passportNum, "user", password);
            if (user == null) {
                resp.setStatus(409); // conflict
                showErrorPage("This username is taken. Please choose a different one.", req, resp);
                return;
            }

            req.getSession(true).setAttribute("user", user);
            resp.sendRedirect(req.getContextPath());
        } catch (SQLException | NoSuchAlgorithmException e) {
            resp.setStatus(500);
            e.printStackTrace();
            showErrorPage("Unknown error when registering", req, resp);
        }
    }

    private void showErrorPage(String message, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errorMessage", message);
        req.setAttribute("backLink", req.getPathInfo());
        getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
    }

}
