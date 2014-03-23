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
    </head>
    <body>
        <h3>Search Visitation Records</h3>
        <form method="post" action="FinancialPatientServlet">
            Start Time: <input type="text" name="start_time"><br>
            End Time: <input type="text" name="end_time"><br>
            Patient ID: <input type="text" name="patient_id"><br>
            
            <input type="submit" value="Search">
        </form>
        
        <br/>
        <br/>
        <table border="1" style="width:300px">
            <tr>
                <td>test1</td>
                <td>test2</td>
                <td>test3</td>
            </tr>
            <tr>
                <td>test1</td>
                <td>test2</td>
                <td>test3</td>
            </tr>
        </table>
    </body>
</html>
