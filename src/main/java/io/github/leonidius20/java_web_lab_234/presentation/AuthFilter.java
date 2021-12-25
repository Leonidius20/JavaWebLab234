package io.github.leonidius20.java_web_lab_234.presentation;

import io.github.leonidius20.java_web_lab_234.domain.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AuthFilter implements Filter {

    public User.Role level;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        var levelString = filterConfig.getInitParameter("level");
        if (!levelString.equalsIgnoreCase("any")) {
            this.level = User.Role.valueOf(levelString.toUpperCase());
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest)request;

        var user = httpRequest.getSession(true).getAttribute("user");

        var httpResponse = ((HttpServletResponse)response);

        if (user == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        } else {
            if (level != null && ((User)user).role() != level) {
                httpResponse.setStatus(403);
                httpResponse.sendRedirect(httpRequest.getContextPath());
            } else
                chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}

}
