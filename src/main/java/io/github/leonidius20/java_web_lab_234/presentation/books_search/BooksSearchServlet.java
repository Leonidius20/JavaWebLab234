package io.github.leonidius20.java_web_lab_234.presentation.books_search;

import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

// servlet is a controller in mvc terms
@WebServlet(name = "helloServlet", value = "/")
public class BooksSearchServlet extends HttpServlet {

    private BooksSearchModelImpl model;

    public BooksSearchServlet() {}

    public void init() {
        model = new BooksSearchModelImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String sortBy = "name";
            if (request.getParameterMap().containsKey("sortBy")) {
                sortBy = request.getParameter("sortBy");
            }
            var books = model.getAll(sortBy);
            request.setAttribute("books", books);
            getServletContext().getRequestDispatcher("/books_catalog.jsp").forward(request, response);
        } catch (SQLException | ServletException e) {
            e.printStackTrace();
            response.sendRedirect("/error.jsp");
        }
    }

    public void destroy() {
        model.close();
    }

}