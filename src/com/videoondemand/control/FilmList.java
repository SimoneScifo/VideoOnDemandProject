package com.videoondemand.control;

import com.Filters.LoggedFilter;
import com.facade.FacadeService;
import com.facade.FacadeServiceImpl;
import com.videoondemand.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/FilmList")
public class FilmList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String order = request.getParameter("order");
        order= order==null?"":order;
        String orderSavedCheck = request.getParameter("orderSavedCheck");
        orderSavedCheck = orderSavedCheck==null?"":orderSavedCheck;

        if(orderSavedCheck.equals("true")){
            Cookie c = new Cookie("orderCookie",order);
            c.setMaxAge(1800);
            response.addCookie(c);
            request.setAttribute("orderCookieCheck", "true");
        }

        request.setAttribute("order", order);

        FacadeService facadeService = FacadeServiceImpl.getInstance();
        request.setAttribute("list", facadeService.findAll(order));
        request.getRequestDispatcher("/listProducts.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String order = "";

        Cookie[] allCookies = request.getCookies();
        for (int i = 0; i < allCookies.length; i++) {
            if (allCookies[i].getName().equals("orderCookie")) {
                order = allCookies[i].getValue();
                request.setAttribute("orderCookieCheck", "true");
            }
        }
        //Controllo se la scelta è checked
        request.setAttribute("order", order);

        FacadeService facadeService = FacadeServiceImpl.getInstance();
        request.setAttribute("list", facadeService.findAll(order));
        request.getRequestDispatcher("/listProducts.jsp").forward(request,response);
    }
}
