<%-- 
    Document   : doctor_searchpatient
    Created on : Mar 19, 2014, 2:37:27 PM
    Author     : Claire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="ece356.Members.Patient" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Records</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.9.1.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script>
        $(function() {
          $( ".datepicker" ).datepicker();
        });
        </script>
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
            <li><a href="DoctorServlet?requestType=3">View Patient</a></li>
            <li class="active"><a href="DoctorServlet?requestType=7">Search Visits</a></li>
        </ul>
        
        Search Visits by Criteria: <br/>
        <form method="post" action="DoctorServlet">
            Patient Name:<input type="text" name="patient_name"><br/>
            Last Visit Date: <input type="text" name="patient_visitdate" class="datepicker"><br/>
            Diagnosis: <input type="text" name="diagnosis"><br/>
            Comment Keyword: <input type="text" name="comment_keyword"><br/>
            Prescriptions: <input type="text" name="prescriptions"><br/>
            Surgery: <input type="text" name="sugery"><br/>
            <br/>           
            <%--should search based on critaria entered then return the patients record , direct to view record page--%>
            <input type="hidden" name="requestType" value="8">
            <input type="submit" value="Search">
        </form>
    </body>
</html>
