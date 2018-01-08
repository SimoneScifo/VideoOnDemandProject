package com.Filters;

import com.videoondemand.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoggedFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class LoggedFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("Entering doFilter()");
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println(req.getServletPath());
        HttpSession session  = req.getSession();
        User u = (User)session.getAttribute("user");
        if(u==null){
            request.getRequestDispatcher("LoginController").forward(request,response);
        }
        System.out.println("Leaving doFilter()");
        chain.doFilter(request,response);
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
