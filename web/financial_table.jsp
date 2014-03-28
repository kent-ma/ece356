<%-- 
    Document   : financial_doctor_table
    Created on : Mar 24, 2014, 9:06:09 PM
    Author     : cynthiachoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
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
            ArrayList<Visit> visits = (ArrayList<Visit>)session.getAttribute("visits");
            %>
            
            Number of matched records: <%= visits.size() %>
            <br/>
            <br/>
            
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
        %>
        </table>
        <a href="financial.jsp">Start a New Search</a>
    </body>
</html>
