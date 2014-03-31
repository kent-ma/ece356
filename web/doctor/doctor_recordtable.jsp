<%-- 
    Document   : doctor_recordtable
    Created on : Mar 31, 2014, 12:22:47 PM
    Author     : cynthiachoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="ece356.Members.Visit" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="/scripts/jquery.min.js"></script>
        <script src="/bootstrap/js/bootstrap.min.js"></script>
        
        <div class="page-header">
            <h1>Doctor Department</h1>
            <span class="label label-default">Freedom is how big your wallet is</span><hr>
            <div class="well well-small">Welcome! ${name}
            <a href="Logout">Logout</a>
            </div>            
        </div>
            
        <ul class="nav nav-tabs">
            <li><a href="DoctorServlet?requestType=0"> Home </a></li>
            <li><a href="DoctorServlet?requestType=1">Add visit record</a></li>
            <li><a href="DoctorServlet?requestType=2">Grant Access</a></li>
            <li><a href="DoctorServlet?requestType=3">View Patient</a></li>
            <li><a href="DoctorServlet?requestType=7">Search Visits</a></li>
        </ul>
        
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
        </table>
        Number of matched records: <%= visits.size() %>
        
        <!--<a href="financial/financial.jsp">Start a New Search</a>-->
        <form method="post" action="DoctorServlet">
            <input type="hidden" name="requestType" value="9">
            <input type="submit" value="Go Back">
        </form>
    </body>
</html>
