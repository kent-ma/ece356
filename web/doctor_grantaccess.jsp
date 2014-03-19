<%-- 
    Document   : Doctor_GrantAccess
    Created on : Mar 19, 2014, 1:01:53 PM
    Author     : Claire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grant Patient Record Access to Doctors </title>
    </head>
    <body>
        <jsp:useBean id="userData" class="ece356.UserData" scope="session"/>
        <b>Logged In As: </b><%out.print(userData.getUserName());%> <br> 
        <b>Role In Hospital: </b><%out.print(userData.getUserName());%>         
        Doctor:<input type="text" name="DoctorID">
        Patient:<input type="text" name="PatientID">
        <input type="submit" value="Grant Access">
    </body>
</html>
