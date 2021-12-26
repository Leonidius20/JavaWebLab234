package io.github.leonidius20.java_web_lab_234.presentation.books.books_search;

import io.github.leonidius20.java_web_lab_234.domain.Book;
import io.github.leonidius20.java_web_lab_234.utils.ErrorPage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// servlet is a controller in mvc terms
@WebServlet(name = "helloServlet", value = "/")
public class BooksSearchServlet extends HttpServlet {

    private BooksSearchModelImpl model;

    public BooksSearchServlet() {}

    @Override
    public void init() throws ServletException {
        try {
            model = new BooksSearchModelImpl();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("BooksSearchServlet: Could not create model");
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String sortBy = "name";
            if (request.getParameterMap().containsKey("sortBy")) {
                sortBy = request.getParameter("sortBy");
            }

            List<Book> books;
            if (request.getParameterMap().containsKey("q")) {
                var query = request.getParameter("q");
                var searchBy = request.getParameter("search_by");
                books = model.getByQuery(query, searchBy, sortBy);
            } else books = model.getAll(sortBy);

            request.setAttribute("books", books);
            getServletContext().getRequestDispatcher("/books_catalog.jsp").forward(request, response);
        } catch (SQLException | ServletException e) {
            e.printStackTrace();
            ErrorPage.show("Error", 500, request, response, getServletContext());
        }
    }

}