<%-- 
    Document   : financial
    Created on : Mar 19, 2014, 1:08:11 PM
    Author     : cynthiachoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Financial Department</title>
    </head>
    <body>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        
        <div class="page-header">
            <h1>Financial Department</h1>
            <span class="label label-default">Freedom is how big your wallet is</span><br>
             <small> Welcome ${name} </small><a href="/ece356/Logout">Logout</a>
        </div>
        
        <form method="post" action="/ece356/FinancialServlet">
            <input type="hidden" name="requestType" value="1">
            <input type="submit" name="submit_doctor" value="Access Records by Doctor ID">
        </form>
        <br/>
        <form method="post" action="/ece356/FinancialServlet">
            <input type="hidden" name="requestType" value="2">
            <input type="submit" name="submit_patient" value="Access Records by Patient ID">
        </form>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
