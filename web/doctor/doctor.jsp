<%-- 
    Document   : Doctor
    Created on : Mar 19, 2014, 12:36:35 PM
    Author     : Claire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctor Index Page</title>
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
            <li class="active"> <a href="FinancialServlet?requestType=0"> Home </a></li>
            <li><a href="DoctorServlet?requestType=1">Add visit record</a></li>
            <li><a href="DoctorServlet?requestType=2">Grant Access</a></li>
            <li><a href="DoctorServlet?requestType=3">View Patient</a></li>
            <li><a href="DoctorServlet?requestType=7">Search Visits</a></li>
        </ul>
            
        This is the home page 
</body>
</html>
