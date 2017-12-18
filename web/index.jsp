<%@ page import="com.videoondemand.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<article>
<img  src="http://www.datamanager.it/wp-content/uploads/2015/12/video-on-demand.jpg"/>
    <%
        if(u==null){
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    %>
</article>
