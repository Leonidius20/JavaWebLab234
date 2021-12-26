package io.github.leonidius20.java_web_lab_234.presentation.librarians.create_librarian;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "create_librarian_servlet", value = "/create-librarian")
public class CreateLibrarianServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("createLibrarian", true);
        getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("createLibrarian", true);
        getServletContext().getRequestDispatcher("/registration").forward(req, resp);
    }
}
