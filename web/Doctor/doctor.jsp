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
        <title>Doctor Index Page</title>
    </head>
    <body>
       <%--  <jsp:useBean id="Doctor" class="ece356.Members.Doctor" scope="session"/> --%>
       <%-- Hello Dr.<%= Doctor.getName() %><br/>--%>
        <li><a href="doctor_grantaccess.jsp">Grant Patient Record Access to Doctors</a></li>
        <li><a href="doctor_searchpatients.jsp">Search for Patient</a></li>
        <li><a href="doctor_addvisitrecord.jsp">Add Visit Record</a></li>      
    </body>
</html>
