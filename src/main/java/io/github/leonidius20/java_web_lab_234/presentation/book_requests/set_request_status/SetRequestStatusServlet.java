package io.github.leonidius20.java_web_lab_234.presentation.book_requests.set_request_status;

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

@WebServlet(name = "set_request_status_servlet", value = "/set-request-status")
public class SetRequestStatusServlet extends HttpServlet {

    private SetRequestStatusModel model;

    @Override
    public void init() throws ServletException {
        try {
            model = new SetRequestStatusModel();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("SetRequestStatusServlet: couldn't create model");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var id = Integer.parseInt(req.getParameter("id"));
            var statusString = req.getParameter("status");
            if (statusString.equalsIgnoreCase("RETURNED")) {
                model.deleteRequest(id);
            } else {
                model.setRequestStatus(id, statusString);
            }
            Logger.getLogger("Set request status").log(Level.INFO, "Status of request with ID " + id + " was changed to " + statusString);
            resp.sendRedirect(getServletContext().getContextPath() + "/orders");
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            ErrorPage.show("Error", 500, req, resp, getServletContext());
        }

    }

}
