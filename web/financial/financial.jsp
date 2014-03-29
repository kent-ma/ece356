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
        Welcome ${name}<br/><br/>
        <form method="post" action="/ece356/FinancialServlet">
            <input type="hidden" name="requestType" value="1">
            <input type="submit" name="submit_doctor" value="Access Records by Doctor ID">
        </form>
        <br/>
        <form method="post" action="/ece356/FinancialServlet">
            <input type="hidden" name="requestType" value="2">
            <input type="submit" name="submit_patient" value="Access Records by Patient ID">
        </form>
    </body>
</html>
