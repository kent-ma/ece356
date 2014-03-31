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
        <form method="post" action="/ece356/DoctorServlet">
            <input type="hidden" name="requestType" value="1">
            <input type="submit" name="doctor_addvisitrecord" value="Add visit record">
        </form>
        <form method="post" action="/ece356/DoctorServlet">
            <input type="hidden" name="requestType" value="2">
            <input type="submit" name="doctor_grantaccess" value="Grant Access">
        </form>    
         <form method="post" action="/ece356/DoctorServlet">
            <input type="hidden" name="requestType" value="3">
            <input type="submit" name="doctor_viewpatient" value="View Patient">
        </form>    
</body>
</html>
