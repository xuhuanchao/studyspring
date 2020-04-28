<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<html>

    <h1>hello world, this is index.jsp</h1>
    <p>
        今天是：<%= LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) %>
    </p>
    <%
        out.println("你好");
    %>

</html>