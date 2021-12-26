package io.github.leonidius20.java_web_lab_234.presentation.auth.my_account;

import io.github.leonidius20.java_web_lab_234.domain.User;
import io.github.leonidius20.java_web_lab_234.utils.ErrorPage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "my_account_servlet", value = "/account")
public class MyAccountServlet extends HttpServlet {

    private MyAccountModelImpl model;

    @Override
    public void init() throws ServletException {
        try {
            model = new MyAccountModelImpl();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("MyAccountServlet: Could not create model");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = (User)req.getSession(true).getAttribute("user");

        if (user.role() == User.Role.USER) {
            try {
                req.setAttribute("requests", model.getBookRequests(user.id()));
            } catch (SQLException e) {
                e.printStackTrace();
                ErrorPage.show("Couldn't load book requests", 500, req, resp, getServletContext());
            }
        }

        getServletContext().getRequestDispatcher("/jsp/account.jsp").forward(req, resp);
    }

}
