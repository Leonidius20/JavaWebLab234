package io.github.leonidius20.java_web_lab_234.presentation.librarians.delete_librarian;

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

@WebServlet(name = "delete_librarian_servlet", value = "/delete-user")
public class DeleteLibrarianServlet extends HttpServlet {

    private DeleteLibrarianModel model;

    @Override
    public void init() throws ServletException {
        try {
            model = new DeleteLibrarianModel();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("DeleteLibrarian: couldn't create model");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var id = Integer.parseInt(req.getParameter("id"));
            model.deleteLibrarian(id);

            var admin = (User)req.getSession().getAttribute("user");
            Logger.getLogger("Delete librarian").log(Level.INFO, "Admin with id " + admin.id() + " deleted librarian with id " + id);
            resp.sendRedirect(getServletContext().getContextPath());
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            ErrorPage.show("Error", 500, req, resp, getServletContext());
        }
    }
}
