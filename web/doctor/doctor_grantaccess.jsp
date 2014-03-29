<%-- 
    Document   : Doctor_GrantAccess
    Created on : Mar 19, 2014, 1:01:53 PM
    Author     : Claire
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ece356.DoctorServlet"%>
<%@page import="ece356.Backend.DatabaseConnection"%>
<%@page import="ece356.Members.Patient"%>
<%@page import="ece356.Members.Doctor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="ece356.Members.Patient" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grant Patient Record Access to Other Doctors </title>
    </head>
    <body>
        <jsp:useBean id="Doctor" class="ece356.Members.Doctor" scope="session"/>
        Hello Dr.<%= Doctor.getName() %><br/>   
        Granting my patient:
        Patient Name: <select name="patientName">
                      <%
            ArrayList<Patient> patients = (ArrayList<Patient>)session.getAttribute("patients");
            %>
            
            </select><br><br>
        
        access to Dr.
        <select name="doctorName">
                      <%
            ArrayList<Doctor> doctors = (ArrayList<Doctor>)session.getAttribute("doctors");
            %>
            
            </select>
        <input type="grant" value="Grant Access">
    </body>
</html>
