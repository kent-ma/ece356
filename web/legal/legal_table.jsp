<%-- 
    Document   : financial_doctor_table
    Created on : Mar 24, 2014, 9:06:09 PM
    Author     : cynthiachoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="ece356.Members.Visit" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visitation Records</title>
    </head>
    <body>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        
        <div class="page-header">
            <h1>Legal Department</h1>
            <span class="label label-default">Freedom is how big your wallet is</span><hr>
            <div class="well well-small">Welcome! ${name}
            <a href="/ece356/Logout">Logout</a>
            </div>            
        </div>
        
        <%--Retrieve visits from servlet--%>
        <table border="1" style="width:800px" class="table table-striped">
        <%
            List<Visit> visits = (List<Visit>)request.getAttribute("visits");
            %>
            
            <%
                if ((visits == null) || (visits.size() == 0)) {
            %>
                    No matched records. <br/>
            <%
                } else {
            %>
                    
                    <thead>
                        <tr>
                            <th>Appointment ID</th>
                            <th>Arrival Time</th>
                            <th>Departure Time</th>
                            <th>Procedure</th>
                            <th>Result</th>
                            <th>Prescription</th>
                            <th>Comment</th>
                            <th>Audit Time</th>
                            <th>Audit by ID</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                    for (Visit v : visits) {
                        %>
                        
                            <tr>
                                <td><%= v.getApptID() %></td>
                                <td><%= v.getArrivalTime() %></td>
                                <td><%= v.getDepartTime() %></td>
                                <td><%= v.getProcedure() %></td>
                                <td><%= v.getResult() %></td>
                                <td><%= v.getPrescription() %></td>
                                <td><%= v.getComment() %></td>
                                <td><%= v.getAuditTime() %></td>
                                <td><%= v.getAuditByID() %></td>
                            </tr>
                        <%
                    }
                }
        %>
        </tbody>
        </table>

        Number of matched records: <%= visits.size() %>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
