<%-- 
    Document   : financial_patient_table
    Created on : Mar 23, 2014, 6:06:56 PM
    Author     : cynthiachoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="ece356.Members.Visit" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%--Retrieve visits from servlet--%>
        <table border="1" style="width:300px">
        <%
            ArrayList<Visit> visits = (ArrayList<Visit>)request.getAttribute("visits");
            
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
