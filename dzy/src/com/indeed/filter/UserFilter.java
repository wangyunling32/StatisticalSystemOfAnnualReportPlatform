package com.indeed.filter;

import com.indeed.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "UserFilter")
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpSession session = ((HttpServletRequest)req).getSession();
        User user = (User)session.getAttribute("user");
        if (user == null)
        {
            req.setAttribute("err_msg","您没有操作权限，请<a href='login.jsp'>登陆</a>");
            req.getRequestDispatcher("error.jsp").forward(req,resp);
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
