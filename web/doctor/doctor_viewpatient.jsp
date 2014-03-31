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
         <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="/scripts/jquery.min.js"></script>
        <script src="/bootstrap/js/bootstrap.min.js"></script>
        
        <div class="page-header">
            <h1>Doctor Department</h1>
            <span class="label label-default">Freedom is how big your wallet is</span><hr>
            <div class="well well-small">Welcome! ${name}
            <a href="Logout">Logout</a>
            </div>            
        </div>
            
        <ul class="nav nav-tabs">
            <li> <a href="DoctorServlet?requestType=0"> Home </a></li>
            <li><a href="DoctorServlet?requestType=1">Add visit record</a></li>
            <li><a href="DoctorServlet?requestType=2">Grant Access</a></li>
            <li class="active"><a href="DoctorServlet?requestType=3">View Patient</a></li>
            <li><a href="DoctorServlet?requestType=7">Search Visits</a></li>
        </ul>
            
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
