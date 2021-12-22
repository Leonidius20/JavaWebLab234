package io.github.leonidius20.java_web_lab_234.presentation;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

// servlet is a controller in mvc terms
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public HelloServlet() {}

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}