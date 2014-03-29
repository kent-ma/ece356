<%-- 
    Document   : staff
    Created on : Mar 28, 2014, 11:29:26 AM
    Author     : kentma
--%>

<%@page import="ece356.Members.Login"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Welcome ${name}</h3>
        <form method="post" action="StaffServlet">
            Patient Name: <input type="text" name="patientName">
            <input type="hidden" name="requestType" value="1">
            <input type="submit" value="Search">
        </form>
    </body>
</html>
