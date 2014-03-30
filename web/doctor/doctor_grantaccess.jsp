<%-- 
    Document   : Doctor_GrantAccess
    Created on : Mar 19, 2014, 1:01:53 PM
    Author     : Claire
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="ece356.DoctorServlet"%>
<%@page import="ece356.Backend.DatabaseConnection"%>
<%@page import="ece356.Members.Patient"%>
<%@page import="ece356.Members.Doctor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ece356.Members.Patient" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grant Patient Record Access to Other Doctors </title>
    </head>
    <body>
        <jsp:useBean id="Doctor" class="ece356.Members.Doctor" scope="session"/>
        Hello Dr.${name}<br/>   
        Granting my patient:
            My Patients: <select name="patients"> <%
            List<Patient> patients = (List<Patient>)request.getAttribute("record");
            %>
            <% for (Patient d : patients) { %>
            <option value="<%= d.getPatientId() %>"><%= d.getName() %></option>
            <% } %>
            </select><br>
<br>
            <br> 
        access to Dr.<select name="doctors"><% List<Doctor> doctors = (List<Doctor>)getServletContext().getAttribute("doctorlist"); %>
            <% for (Doctor d : doctors) { %>
            <option value="<%= d.getDoctorId() %>"><%= d.getName() %></option>
            <% } %>
            </select><br>
            <input type="hidden" name="requestType" value="4">
            <input type="submit" value="Grant Access">
    </body>
</html>
