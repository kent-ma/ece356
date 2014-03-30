<%-- 
    Document   : financial_doctor
    Created on : Mar 23, 2014, 2:22:16 PM
    Author     : cynthiachoi
--%>

<%@page import="java.util.List"%>
<%@page import="ece356.Members.Doctor"%>
<%@page import="ece356.Members.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <h3>Editing Patient</h3>
        <form method="post" action="StaffServlet">
        <% Patient p = (Patient)request.getAttribute("patient"); %>
        <input type="hidden" name="patientId" value="<%= p.getPatientId() %>">
        <input type="hidden" name="patientName" value="<%= request.getParameter("patientName") %>">
        <input type="hidden" name="patientOHIP" value="<%= request.getParameter("patientOHIP") %>">
        <input type="hidden" name="requestType" value="4">
            Name:           <input type="text" name="name" value="<%= p.getName() %>"><br>
            Address:        <input type="text" name="address" value="<%= p.getAddress() %>"><br>
            Phone Number:   <input type="text" name="phone" value="<%= p.getPhoneNum() %>"><br>
            DOB:            <input type="text" name="dob" value="<%= p.getDob() %>"><br>
            SIN:            <input type="text" name="sin" value="<%= p.getSin() %>"><br>
            OHIP:           <input type="text" name="hcn" value="<%= p.getHealthCardNo() %>"><br>
            Status:          <input type="text" name="status" value="<%= p.getHealthStatus() %>"><br>
            Default Doctor: <select name="defdoc"><% List<Doctor> doctors = (List<Doctor>)getServletContext().getAttribute("doctorlist"); %>
            <% for (Doctor d : doctors) { %>
                <option value="<%= d.getDoctorId() %>"><%= d.getName() %></option>
            <% } %>
            </select>
            <input type="submit" value="Submit Changes">
        </form>
    </body>
</html>