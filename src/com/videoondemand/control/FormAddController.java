package com.videoondemand.control;

import com.dao.FactoryDAO;
import com.dao.FilmDAO;
import com.dao.dto.FilmDTO;
import com.facade.FacadeService;
import com.facade.FacadeServiceImpl;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.videoondemand.model.Genre;
import com.videoondemand.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simone on 28/11/2017.
 */
@WebServlet("/FormAddController")
public class FormAddController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String year = request.getParameter("year");
        String id = request.getParameter("id");
        String action = request.getParameter("action");

        FacadeService facadeService = FacadeServiceImpl.getInstance();

        List<String> errors = validate(title, Integer.parseInt(genre), year);
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("title", title);
            request.setAttribute("genre", genre);
            request.setAttribute("year", year);
            doGet(request, response);
        } else {
            FilmDTO filmDTO = new FilmDTO();
            filmDTO.title = title;
            filmDTO.id_genre = Integer.valueOf(genre);
            filmDTO.year = Integer.valueOf(year);
            if (action.equals("add")) {

                facadeService.insert(filmDTO);
            } else if (action.equals("edit")) {
                filmDTO.id = Integer.parseInt(id);
                facadeService.update(filmDTO);
            }
        }
        response.sendRedirect("FilmList");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        FacadeService facadeService = FacadeServiceImpl.getInstance();

        if (action.equals("add")) {
            request.setAttribute("action", "add");
            request.setAttribute("genres", facadeService.findAllGenres());
            request.getRequestDispatcher("/formAddProduct.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            String id = request.getParameter("id");
            FilmDTO f = facadeService.findByID(Integer.parseInt(id));
            facadeService.delete(f);
            request.getRequestDispatcher("FilmList").forward(request, response);
        } else if (action.equals("edit")) {

            String id = request.getParameter("id");
            FilmDTO f = facadeService.findByID(Integer.parseInt(id));
            request.setAttribute("id", f.id);
            request.setAttribute("title", f.title);
            request.setAttribute("genre", f.id_genre);
            request.setAttribute("year", f.year);
            request.setAttribute("genres", facadeService.findAllGenres());
            request.setAttribute("action", "edit");
            request.getRequestDispatcher("/formAddProduct.jsp").forward(request, response);
        }
    }

    public List<String> validate(String title, int genre, String year) {
        List<String> errors = new ArrayList<>();

        if (!checkTitle(title)) {
            errors.add("Il titolo deve avere una lunghezza di almeno due caratteri!");
        }
        if (!checkYearIsNumber(year)) {
            errors.add("L'anno deve essere un numero valido!");
        }
        if (!checkYear(year)) {
            errors.add("L'anno deve essere compreso tra il 1900 e il 2017");
        }
        return errors;
    }

    private boolean checkTitle(String title) {
        if (title.length() >= 2) {
            return true;
        }
        return false;
    }

    private boolean checkYearIsNumber(String year) {
        String regex = "\\d+";
        if (year.matches(regex)) {
            return true;
        }
        return false;
    }

    private boolean checkYear(String yearString) {
        if (checkYearIsNumber(yearString)) {
            int year = Integer.valueOf(yearString);
            if (year >= 1900 && year <= LocalDate.now().getYear()) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }
}
