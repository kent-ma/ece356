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
         <%--Retrieve all patients of the doctor from servlet--%>
         Hello Dr.${name}<br/> 
           
           list of my patient:<br/>
            
            <select name="patients"> <%
            List<Patient> patients = (List<Patient>)request.getAttribute("record");
            %>
            <% for (Patient p : patients) { %>
            <option name ="patientID" value="<%= p.getPatientId() %>"><%= p.getName() %></option>
            <% } %>
            </select>
            <%--should return the patient's record, direct to view record page--%>
            <form method="post" action="DoctorServlet">
                <input type="hidden" name="requestType" value="3">
                <input type="submit" value="View Record">
            </form>

            Search my patients:<br/>        
            
        <form method="post" action="DoctorServlet">
            Patient Name:<input type="text" name="patient_name"><br/>
            Patient ID: <input type="text" name="patient_id"><br/>
            Last Visit Date: <input type="text" name="patient_visitDate"><br/>
            <br/>           
            <%--should search based on critaria entered then return the patients record , direct to view record page--%>
            <input type="hidden" name="requestType" value="6">
            <input type="submit" value="Search">
        </form>
    </body>
</html>
