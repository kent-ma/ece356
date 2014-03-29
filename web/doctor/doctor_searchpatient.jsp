<%-- 
    Document   : doctor_searchpatient
    Created on : Mar 19, 2014, 2:37:27 PM
    Author     : Claire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="ece356.Members.Patient" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient Records</title>
    </head>
    <body>
        
        <%-- get all the patients record under the doctor--%>
        <table border="1" style="width:800px">
        <%
            ArrayList<Patient> patients = (ArrayList<Patient>)session.getAttribute("patients");
            %>
            
            Number of matched records: <%= patients.size() %>
            <br/>
            <br/>
            
            <%
            for (Patient p : patients) {
                %>
                <tr>
                    <td><%= p.getPatientId() %></td>
                    <td><%= p.getSin() %></td>
                    <td><%= p.getHealthCardNo() %></td>
                    <td><%= p.getHealthStatus() %></td>
                    <td><%= p.getPhoneNum()%></td>
                    <td><%= p.getDefDoctorId() %></td>
                </tr>
                <%
            }
        %>
        </table>
    
        <h3>Search Patient Records</h3>
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
    </body>
</html>
