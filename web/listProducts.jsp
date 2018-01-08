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

    String order = (String) request.getAttribute("order");
    order = order==null?"":order;

    String orderCookieCheck = (String)request.getAttribute("orderCookieCheck");
%>
    <form action='<%=response.encodeURL("FilmList")%>' method="post">
        <select name="order">
            <option value="titleDesc"<%if(order.equals("titleDesc")){%> selected <%}%>>A-Z</option>
            <option value="yearAsc"<%if(order.equals("yearAsc")){%> selected <%}%>>ANNO</option>
        </select>
        <br>


        <input type="checkbox" name="orderSavedCheck" value="true" <%if(orderCookieCheck!=null){%>checked<%}%>>Ricorda questa scelta<br>

        <input type="submit" value="Ordina">
    </form>
    <table class="table table-striped table-dark">
        <thead>
        <tr>
            <th scope="col">TITOLO</th>
            <th scope="col">GENERE</th>
            <th scope="col">ANNO</th>
            <th scope="col">COPERTINA</th>

        </tr>
        </thead>
        <tbody>
        <%for(FilmDTO f : listFilm){
        %>
        <tr>
            <td><%=f.title%></td>
            <td><%=f.getGenresMap().get(f.id_genre).getName()%></td>
            <td><%=f.year%></td>
            <%
            if (!(f.cover == null)){
                if(!(f.cover.equalsIgnoreCase("null"))){
            %>
            <td><img src="http://localhost:8080/images/<%=f.cover%>" height="100px" width="100px"></td>
            <%
                    }
                }
                %>
            <td>

                <a href='<%=response.encodeURL("FormAddController?action=edit&id=" + f.id)%>'>
                <input type="button" name="edit" id="edit" value="EDIT" ></a></td>
            <td>

            <a href='<%=response.encodeURL("FormAddController?action=delete&id=" + f.id)%>'>
                <input type="button" name="delete" id="delete" value="DELETE" ></a></td>
            <td></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <a href='<%=response.encodeURL("FormAddController?action=add")%>'>Modulo carica prodotto</a>
</article>
