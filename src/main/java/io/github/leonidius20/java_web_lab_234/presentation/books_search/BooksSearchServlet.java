package io.github.leonidius20.java_web_lab_234.presentation.books_search;

import java.io.*;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;

// servlet is a controller in mvc terms
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class BooksSearchServlet extends HttpServlet {

    private BooksSearchModelImpl model;

    public BooksSearchServlet() {}

    public void init() {
        model = new BooksSearchModelImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            var books = model.getAll();
            request.setAttribute("books", books);
            getServletContext().getRequestDispatcher("books_catalog.jsp").forward(request, response);
        } catch (SQLException | ServletException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    public void destroy() {
        model.close();
    }

}