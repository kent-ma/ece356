<%-- 
    Document   : doctor_addvisitrecord
    Created on : Mar 19, 2014, 2:38:33 PM
    Author     : Claire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <body>
        <h3>Search Visitation Records</h3>
        <form method="post" action="DoctorPatientServlet">
            Patient Name:<input type="text" name="patient_name"><br/>
            <br/>
            Patient ID: <input type="text" name="patient_id"><br/>
            <br/>            
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
            <input type="submit" value="Search">
        </form>
        
        
        
        
        
        
    <p><a href="/Logout">Logout</a>
    </body>
</html>
