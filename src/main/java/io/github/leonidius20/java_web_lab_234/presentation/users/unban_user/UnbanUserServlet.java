package io.github.leonidius20.java_web_lab_234.presentation.users.unban_user;

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

@WebServlet(name = "unban_user_servlet", value = "/unban-user")
public class UnbanUserServlet extends HttpServlet {

    private UnbanUserModel model;

    @Override
    public void init() throws ServletException {
        try {
            model = new UnbanUserModel();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("UnbanUserServlet: couldn't create model");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var id = Integer.parseInt(req.getParameter("id"));
            model.unbanUser(id);
            Logger.getLogger("Ban").log(Level.INFO, "User with id " + id + " was unbanned");
            resp.sendRedirect(getServletContext().getContextPath());
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            ErrorPage.show("Error", 500, req, resp, getServletContext());
        }

    }

}
