package com.videoondemand.control;

import com.facade.FacadeService;
import com.facade.FacadeServiceImpl;
import com.videoondemand.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FacadeService facadeService = FacadeServiceImpl.getInstance();

        String username = request.getParameter("usernameLogin");
        String password = request.getParameter("passwordLogin");
        User u = facadeService.findByCredentials(username,password);
        if(u!=null){
            HttpSession session = request.getSession();
            session.setAttribute("user", u);
           request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
        else{
            String advise = "Username o password errati";
            request.setAttribute("error", advise);
            doGet(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request,response);
    }
}
