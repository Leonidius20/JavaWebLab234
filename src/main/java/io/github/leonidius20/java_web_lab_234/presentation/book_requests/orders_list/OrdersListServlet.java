package io.github.leonidius20.java_web_lab_234.presentation.book_requests.orders_list;

import io.github.leonidius20.java_web_lab_234.utils.ErrorPage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "orders_list_servlet", value = "/orders")
public class OrdersListServlet extends HttpServlet {

    private OrdersListModelImpl model;

    @Override
    public void init() throws ServletException {
        try {
            model = new OrdersListModelImpl();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("OrdersListServlet: Could not create model");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var orders = model.getAllOrders();
            req.setAttribute("orders", orders);
            getServletContext().getRequestDispatcher("/jsp/order-list.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            ErrorPage.show("Error loading orders", 500, req, resp, getServletContext());
        }
    }

}
