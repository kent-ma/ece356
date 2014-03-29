<%-- 
    Document   : staff
    Created on : Mar 28, 2014, 11:29:26 AM
    Author     : kentma
--%>

<%@page import="java.util.List"%>
<%@page import="ece356.Members.Doctor"%>
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
        <p><b>Get Patient Info</b>
        <form method="post" action="StaffServlet">
            Patient Name: <input type="text" name="patientName"><br>
            Patient OHIP: <input type="text" name="patientOHIP"><br>
            <input type="hidden" name="requestType" value="1">
            <input type="submit" value="Search">
        </form>
        <p><b>Get Appointment Info</b>
        <form method="post" action="StaffServlet">
            Appt Time: <input type="datetime" name="apptTime"><br>
            Doctor Name: <select name="doctors"><% List<Doctor> doctors = (List<Doctor>)request.getAttribute("doctorlist"); %>
            <% for (Doctor d : doctors) { %>
            <option value="<%= d.getDoctorId() %>"><%= d.getName() %></option>
            <% } %>
            </select><br>
            <input type="hidden" name="requestType" value="2">
            <input type="submit" value="Search">
        </form>
        <p><a href="/Logout">Logout</a>
    </body>
</html>
