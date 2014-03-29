<%-- 
    Document   : financial_doctor
    Created on : Mar 23, 2014, 2:22:16 PM
    Author     : cynthiachoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Visitation Records</title>
    </head>
    <body>
        <h3>Search Visitation Records</h3>
        <form method="post" action="/ece356/FinancialDoctorServlet">
            Start Date: 
            <br/>
            Year: <input type="text" name="start_year">
            Month: <input type="text" name="start_month">
            Day: <input type="text" name="start_day">
            <br/>
            <br/>
            End Date: 
            <br/>
            Year: <input type="text" name="end_year">
            Month: <input type="text" name="end_month">
            Day: <input type="text" name="end_day">
            <br/>
            <br/>
            Doctor ID: <input type="text" name="doctor_id"><br>
            
            <input type="submit" value="Search">
        </form>
    </body>
</html>
