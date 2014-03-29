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
        Welcome ${name}<br/><br/>
        <h3>Search Visitation Records</h3>
        <form method="post" action="/ece356/FinancialServlet">
        <input type="hidden" name="requestType" value="4">
            Start Date: <input type="text" name="start_date" class="datepicker">
            End Date: <input type="text" name="end_date" class="datepicker">
            Patient ID: <input type="text" name="patient_id"><br/>
            
            <input type="submit" value="Search">
        </form>
    </body>
</html>
