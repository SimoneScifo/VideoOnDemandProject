<%@ page import="com.videoondemand.model.DataModel" %>
<%@ page import="com.videoondemand.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dao.FactoryDAO" %>
<%@ page import="com.dao.FilmDAO" %>
<%@ page import="com.dao.dto.FilmDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<article>
<%
    List<FilmDTO> listFilm = (List<FilmDTO>)request.getAttribute("list");

%>
    <table class="table table-striped table-dark">
        <thead>
        <tr>
            <th scope="col">TITOLO</th>
            <th scope="col">GENERE</th>
            <th scope="col">ANNO</th>
        </tr>
        </thead>
        <tbody>
        <%for(FilmDTO f : listFilm){
        %>
        <tr>
            <td><%=f.title%></td>
            <td><%=f.getGenresMap().get(f.id_genre).getName()%></td>
            <td><%=f.year%></td>
            <td></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <a href="FormAddController">Modulo carica prodotto</a>
</article>