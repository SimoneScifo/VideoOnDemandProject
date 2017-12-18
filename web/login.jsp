<%--
  Created by IntelliJ IDEA.
  User: Simone
  Date: 18/12/2017
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<article>
    <h1>LOGIN PAGE</h1>
    <%
        String error = (String) request.getAttribute("error");
        error = error==null?"":(String) request.getAttribute("error");
    %>
    <form action="LoginController" method="post">
    <input type="text" name="usernameLogin"/>
        <br>
    <input type="password" name="passwordLogin">
        <br>
        <input type="submit" value="LOGIN">
    </form>
    <br>
    <h1 style="color: red;"><%=error%></h1>
    <a href="register.jsp">SIGN UP</a>
</article>

