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
            <li class="active"><a href="DoctorServlet?requestType=2">Grant Access</a></li>
            <li><a href="DoctorServlet?requestType=3">View Patient</a></li>
            <li><a href="DoctorServlet?requestType=7">Search Visits</a></li>
        </ul>        

        Granting my patient:<br/>
        <form method="post" action="DoctorServlet">
            <select name="patientID">
                <%
                    List<Patient> patients = (List<Patient>)request.getAttribute("record");
                %>
                <% for (Patient p : patients) { %>
                    <option value="<%= p.getPatientId() %>"><%= p.getName() %></option>
                <% } %>
            </select>
            
            <br>
            to Dr.
            <br/>
            <select name="docID">
                <% List<Doctor> doctors = (List<Doctor>)request.getAttribute("doctorlist"); %>
                <% for (Doctor d : doctors) { %>
                    <option value="<%= d.getDoctorId() %>"><%= d.getName() %></option>
                <% } %>
            </select>
            <br>
            <input type="hidden" name="requestType" value="4">
            <input type="submit" value="Grant Access">
            </form>
    </body>
</html>
