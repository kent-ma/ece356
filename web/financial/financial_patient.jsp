<%-- 
    Document   : financial_patient
    Created on : Mar 23, 2014, 2:22:01 PM
    Author     : cynthiachoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Visitation Records</title>
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
            <h1>Financial Department</h1>
            <span class="label label-default">Freedom is how big your wallet is</span><hr>
            <div class="well well-small">Welcome! ${name}
            <a href="Logout">Logout</a>
            </div>            
        </div>
            
        <ul class="nav nav-tabs">
            <li> <a href="FinancialServlet?requestType=0"> Home </a></li>
            <li><a href="FinancialServlet?requestType=1">Access Records by Doctor ID</a></li>
            <li class="active"><a href="FinancialServlet?requestType=2">Access Records by Patient ID</a></li>
        </ul>
            
            
        <h3>Search Patient Visitation Records</h3>
        <form method="post" action="FinancialServlet">
        <input type="hidden" name="requestType" value="4">
            Start Date: <input type="text" name="start_date" class="datepicker">
            End Date: <input type="text" name="end_date" class="datepicker">
            Patient Name: <input type="text" name="patient_id"><br/>
            
            <input type="submit" value="Search">
        </form>
    </body>
</html>
