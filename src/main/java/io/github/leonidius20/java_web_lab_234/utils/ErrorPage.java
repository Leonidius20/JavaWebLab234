package io.github.leonidius20.java_web_lab_234.utils;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ErrorPage {

    public static void show(String message, int code, HttpServletRequest req, HttpServletResponse resp, ServletContext context) throws ServletException, IOException {
        resp.setStatus(code);
        req.setAttribute("errorMessage", message);
        req.setAttribute("backLink", req.getPathInfo());
        context.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
    }

}
