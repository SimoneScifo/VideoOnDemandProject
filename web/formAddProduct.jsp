<%@ page import="java.util.List" %>
<%@ page import="com.dao.GenreDAO" %>
<%@ page import="com.dao.FilmDAO" %>
<%@ page import="com.dao.jdbc.GenreJdbcDAOImpl" %>
<%@ page import="com.videoondemand.model.Genre" %>
<%@ page import="com.dao.jdbc.JdbcDAOFactory" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String titleForm = request.getParameter("title");
String genreForm = request.getParameter("genre");
String yearForm = request.getParameter("year");
    titleForm=(titleForm==null)?"":titleForm;
    genreForm=(genreForm==null)?"0":genreForm;
    yearForm=(yearForm==null)?"":yearForm;
%>
<article>
<h1>Modulo Carica Prodotto</h1>
<br>
    <form action="/FormAddController" method="post">
        Title
        <br>
        <input type="text" name="title" value='<%=titleForm%>'/>
        <br>
        Genere
        <br>
        <select name="genre">
            <%
                Map <Integer,Genre> genres = (request.getAttribute("genres"))instanceof Map? (Map)request.getAttribute("genres"):null;
                for (Map.Entry<Integer, Genre> entry : genres.entrySet()){
                    if(entry.getKey()==Integer.parseInt(genreForm)){
                        %>
            <option value="<%=entry.getKey()%>" selected><%=entry.getValue().getName()%></>
                <%
                    }
                    else{
            %>
            <option value="<%=entry.getKey()%>"><%=entry.getValue().getName()%></>
            <%
                    }
                }
            %>
        </select>
        <br>
        Anno
        <br>
        <input type="text" name="year" value='<%=yearForm%>'/>
        <input type="submit" value="Salva"/>
    </form>
    <%List<String> errors =(List<String>) request.getAttribute("errors");
    if(errors!=null&&!errors.isEmpty()){
        for(String e: errors){
            %>
        <h3 style="color: red"><%=e%></h3>
    <%
        }
        %>
    <%
    }
    else if(errors!=null && errors.isEmpty()){
        response.sendRedirect("FilmList");
    }
    %>
</article>