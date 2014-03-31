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
    </head>
    <body>
        Search Visits by Criteria: <br/>
        <form method="post" action="DoctorServlet">
            Patient Name:<input type="text" name="patient_name"><br/>
            Last Visit Date: <input type="text" name="patient_visitdate"><br/>
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
