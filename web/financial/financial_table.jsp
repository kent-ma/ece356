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
        <%--Retrieve visits from servlet--%>
        <table border="1" style="width:800px">
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
                    Number of matched records: <%= visits.size() %>
                    <br/>
                    <br/>
                    <tr>
                        <td>Appointment ID</td>
                        <td>Arrival Time</td>
                        <td>Departure Time</td>
                        <td>Procedure</td>
                        <td>Result</td>
                        <td>Prescription</td>
                        <td>Comment</td>
                        <td>Audit Time</td>
                        <td>Audit by ID</td>
                    </tr>
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
        <!--<a href="financial/financial.jsp">Start a New Search</a>-->
        <form method="post" action="/ece356/FinancialServlet">
            <input type="hidden" name="requestType" value="5">
            <input type="submit" value="Start a New Search">
        </form>
    </body>
</html>
