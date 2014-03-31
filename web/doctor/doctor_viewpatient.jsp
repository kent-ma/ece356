<%-- 
    Document   : doctor_viewpatient
    Created on : Mar 30, 2014, 6:45:13 PM
    Author     : Claire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ece356.Members.Doctor"%>
<%@page import="java.util.List"%>
<%@page import="ece356.Members.Patient"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View all Patient and Assign</title>
    </head>
    <body>
         Hello Dr.${name}
         <br/> 
            list of my patient:<br/>
            <form method="post" action="DoctorServlet">
            <select name="patientID"> 
                <%
                    List<Patient> patients = (List<Patient>)request.getAttribute("record");
                %>
                <% for (Patient p : patients) { %>
                    <option value="<%= p.getPatientId() %>"><%= p.getName() %></option>
                <% } %>
            </select>
            
            <%--should return the patient's record, direct to view record page--%>
                <input type="hidden" name="requestType" value="6">
                <input type="submit" value="View Record">
            </form>
    </body>
</html>
