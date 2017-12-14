package com.videoondemand.control;

import com.facade.FacadeService;
import com.facade.FacadeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FilmList")
public class FilmList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FacadeService facadeService = FacadeServiceImpl.getInstance();
        request.setAttribute("list", facadeService.findAll());
        getServletContext().getRequestDispatcher("/listProducts.jsp").forward(request,response);
    }
}
