<%@ page import="java.util.List" %>
<%@ page import="com.dao.GenreDAO" %>
<%@ page import="com.dao.FilmDAO" %>
<%@ page import="com.dao.jdbc.GenreJdbcDAOImpl" %>
<%@ page import="com.videoondemand.model.Genre" %>
<%@ page import="com.dao.jdbc.JdbcDAOFactory" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String titleForm = (String) request.getAttribute("title");
    String id = String.valueOf(request.getAttribute("id"));
    String genreForm = String.valueOf(request.getAttribute("genre"));
    String yearForm = String.valueOf(request.getAttribute("year"));
    String cover = (String) request.getAttribute("cover");
    String action = (String)request.getAttribute("action");

    titleForm=(titleForm==null||titleForm.equals("null"))?"":titleForm;
    genreForm=(genreForm==null||genreForm.equals("null"))?"0":genreForm;
    yearForm=(yearForm==null||yearForm.equals("null"))?"":yearForm;
    cover = (cover==null||cover.equals("null"))?"":cover;
    action=(action==null||action.equals("null"))?"add":action;
    id=(id==null)?"0":id;
%>
<article>
<h1>Modulo Carica Prodotto</h1>
<br>
    <form action="FormAddController?action=<%=action%>" method="post" enctype="multipart/form-data">
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
        <input type="text" name="year" value='<%=yearForm%>'/><br>
        <input name="cover" type="file" value="<%=cover%>"><br>
        <%
        if(action.equals("add")){
        %>
        <input type="submit" value="Salva"/>
        <%
            }else{
        %>
        <input type="submit" value="Update">
        <input type="text" hidden="hidden" name="id" value='<%=id%>'>
        <%
            }
        %>
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