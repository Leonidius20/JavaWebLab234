package io.github.leonidius20.java_web_lab_234.presentation.books_search;

import java.io.*;
import java.sql.SQLException;

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
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        try {
            var books = model.getAll();
            for (var book: books) {
                out.println("<ul>" + book.name() + "</ul>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.println("<h1>" + "g" + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
        model.close();
    }

}