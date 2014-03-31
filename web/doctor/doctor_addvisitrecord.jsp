<%-- 
    Document   : doctor_addvisitrecord
    Created on : Mar 19, 2014, 2:38:33 PM
    Author     : Claire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ece356.Members.Doctor"%>
<%@page import="ece356.Members.Visit"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enter Visitation Records</title>
    </head>
    
    <body>
        <h3>Enter Visitation Records</h3>
        <form method="post" action="/ece356/DoctorServlet">
            
            <% Visit v = (Visit)request.getAttribute("visit"); %>
            <input type="hidden" name="apptId" value="<%= v.getApptID() %>">
            <input type="hidden" name="doctors" value="<%= request.getParameter("doctors") %>">
            <input type="hidden" name="apptTime" value="<%= request.getParameter("apptTime") %>">
            <input type="hidden" name="requestType" value="5">
            Patient Name:<input type="text" name="visit_patient_name"><br/>
            Patient ID: <input type="text" name="visit_patient_id"><br/>
            Procedure:<input type="text" name="visit_procedure"><br/>
            Result:<input type="text" name="visit_result"><br/>
            Prescription: <input type="text" name="visit_prescription"><br/>
            Diagnosis<input type="text" name="visit_diagnosis"><br/>
            Arrival Time<input type="text" name="visit_arrival"><br/>
            Departure Time<input type="text" name="visit_departure"><br/>
            Comment<input type="text" name="visit_comment"><br/>
            <input type="submit" value="Add">
        </form>
    <p><a href="/Logout">Logout</a>
    </body>
</html>
