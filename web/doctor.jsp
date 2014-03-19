<%-- 
    Document   : Doctor
    Created on : Mar 19, 2014, 12:36:35 PM
    Author     : Claire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctor Welcome Page</title>
    </head>
    <body>
        <jsp:useBean id="userData" class="ece356.UserData" scope="session"/>
        Hello Dr.<%= userData.getUserName() %><br/>
        <li><a href="Doctor_GrantAccess.jsp">Grant Patient Record Access to Doctors</a></li>
        <li><a href="Doctor_GrantAccess.jsp">Grant Patient Record Access to Doctors</a></li>
        
        
       
    </body>
</html>
