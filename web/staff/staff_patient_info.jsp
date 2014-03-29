<%-- 
    Document   : staff
    Created on : Mar 28, 2014, 11:29:26 AM
    Author     : kentma
--%>

<%@page import="java.util.List"%>
<%@page import="ece356.Members.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%--Retrieve visits from servlet--%>
        <table border="1" style="width:800px">
        <%
            List<Patient> patients = (List<Patient>)request.getAttribute("record");
            %>
            
            Number of matched records: <%= patients.size() %>
            <br/>
            <br/>
            <tr>
                <td>Name</td>
                <td>Address</td>
                <td>Phone Number</td>
                <td>DOB</td>
                <td>SIN</td>
                <td>OHIP Number</td>
                <td>Status</td>
            </tr>
            <%
            for (Patient p : patients) {
                %>
                <tr>
                    <td><%= p.getName() %></td>
                    <td><%= p.getAddress() %></td>
                    <td><%= p.getPhoneNum() %></td>
                    <td><%= p.getDob().toString() %></td>
                    <td><%= p.getSin() %></td>
                    <td><%= p.getHealthCardNo() %></td>
                    <td><%= p.getHealthStatus() %></td>
                </tr>
                <%
            }
        %>
        </table>
    <p><a href="/StaffServlet">Back to Staff Home</a>
    <p><a href="/Logout">Logout</a>
    </body>
</html>
