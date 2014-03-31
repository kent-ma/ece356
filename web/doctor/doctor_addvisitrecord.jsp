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
            <li> <a href="FinancialServlet?requestType=0"> Home </a></li>
            <li class="active"><a href="DoctorServlet?requestType=1">Add visit record</a></li>
            <li><a href="DoctorServlet?requestType=2">Grant Access</a></li>
            <li><a href="DoctorServlet?requestType=3">View Patient</a></li>
            <li><a href="DoctorServlet?requestType=7">Search Visits</a></li>
        </ul>
        
        <h3>Enter Visitation Records</h3>
        <form method="post" action="/ece356/DoctorServlet">
           
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
    </body>
</html>
