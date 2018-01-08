package com.videoondemand.control;

import com.dao.FactoryDAO;
import com.dao.FilmDAO;
import com.dao.dto.FilmDTO;
import com.facade.FacadeService;
import com.facade.FacadeServiceImpl;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.videoondemand.model.Genre;
import com.videoondemand.model.Product;
import com.videoondemand.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simone on 28/11/2017.
 */
@WebServlet("/FormAddController")
@MultipartConfig
public class FormAddController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Immagine

        final String path = "http://localhost:8080/images/";
        final Part filePart = request.getPart("cover");
        final String fileName;
        if(filePart.getSize()>0){ // Ha caricato l'immagine
            fileName = getFileName(filePart);
            String finalPath = "D:\\Simone\\Documenti\\ELIS\\JAVA\\JAVA EE\\apache-tomcat-8.5.23\\webapps\\images\\" + fileName;
            try(OutputStream out= new FileOutputStream(new File(finalPath));
                InputStream filecontent = filePart.getInputStream();){
                int read = 0;
                final byte[] bytes = new byte[1024];
                while((read= filecontent.read(bytes))!= -1){
                    out.write(bytes, 0, read);
                }
            } catch (FileNotFoundException fne){
                fne.printStackTrace();
            }

        }
        else{
            fileName="NULL";
        }


        //Fine immagine

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
            request.setAttribute("cover", fileName); //Immagine
            doGet(request, response);
        } else {
            FilmDTO filmDTO = new FilmDTO();
            filmDTO.title = title;
            filmDTO.id_genre = Integer.valueOf(genre);
            filmDTO.year = Integer.valueOf(year);
            filmDTO.cover = fileName;
            if (action.equals("add")) {
                facadeService.insert(filmDTO);
            } else if (action.equals("edit")) {
                filmDTO.id = Integer.parseInt(id);
                facadeService.update(filmDTO);
            }
        }
        response.sendRedirect(response.encodeURL("FilmList"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        action = action==null?"add":request.getParameter("action");
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

    private String getFileName(final Part part){
        for(String content: part.getHeader("content-disposition").split(";")){
            if(content.trim().startsWith("filename")){
                return content.substring(
                        content.indexOf('=')+1).trim().replace("\"","");
            }
        }
        return null;
    }
}
