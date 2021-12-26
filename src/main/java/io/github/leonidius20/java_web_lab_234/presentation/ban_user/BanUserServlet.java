package io.github.leonidius20.java_web_lab_234.presentation.ban_user;


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

@WebServlet(name = "ban_user_servlet", value = "/ban-user")
public class BanUserServlet extends HttpServlet {

    private BanUserModel model;

    @Override
    public void init() throws ServletException {
        try {
            model = new BanUserModel();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("BanUserServlet: couldn't create model");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var id = Integer.parseInt(req.getParameter("id"));
            model.banUser(id);
            Logger.getLogger("Ban").log(Level.INFO, "User with id " + id + " was banned");
            resp.sendRedirect(getServletContext().getContextPath());
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            ErrorPage.show("Error", 500, req, resp, getServletContext());
        }

    }

}
