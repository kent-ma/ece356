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
        <title>Grant Patient Record Access to Other Doctors </title>
    </head>
    <body>
        <jsp:useBean id="Doctor" class="ece356.Members.Doctor" scope="session"/>
        Hello Dr.<%= Doctor.getName() %><br/>         
        Doctor:<input type="text" name="DoctorID">
        
        <input type="grant" value="Grant Access">
    </body>
</html>
