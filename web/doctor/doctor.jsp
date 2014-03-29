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
        <li><a href="doctor_grantaccess.jsp">Grant Patient Record Access to Doctors</a></li>
        <li><a href="doctor_searchpatients.jsp">Search for Patient</a></li>
        <li><a href="doctor_addvisitrecord.jsp">Add Visit Record</a></li>      
     <form method="post" action="/ece356/DoctorServlet">
            <input type="hidden" name="requestType" value="1">
            <input type="submit" name="doctor_addvisitrecord" value="Add visit record">
        </form>
        <br/>
        <form method="post" action="/ece356/DoctorServlet">
            <input type="hidden" name="requestType" value="2">
            <input type="submit" name="doctor_grantaccess" value="Grant Access">
        </form>    
         <form method="post" action="/ece356/DoctorServlet">
            <input type="hidden" name="requestType" value="3">
            <input type="submit" name="doctor_searchpatient" value="Search Patient">
        </form>    



</body>
</html>
