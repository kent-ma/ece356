<%-- 
    Document   : staff
    Created on : Mar 28, 2014, 11:29:26 AM
    Author     : kentma
--%>

<%@page import="ece356.Members.Appointment"%>
<%@page import="java.util.List"%>
<%@page import="ece356.Members.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>STAFF - Appointment Info</title>
    </head>
    <body>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="/scripts/jquery.min.js"></script>
        <script src="/bootstrap/js/bootstrap.min.js"></script>
        
        <div class="page-header">
            <h1>Staff Department</h1>
            <span class="label label-default">Freedom is how big your wallet is</span><hr>
            <div class="well well-small">Welcome! ${name}
            <a href="Logout">Logout</a>
            </div>            
        </div>
        
        <%--Retrieve visits from servlet--%>
        <table border="1" style="width:800px" class="table table-striped">
        <%
            List<Appointment> appointments = (List<Appointment>)request.getAttribute("record");
            %>
            <thead>
                <tr>
                    <td>Patient</td>
                    <td>Doctor</td>
                    <td>Room Number</td>
                    <td>Time</td>
                    <td>Type</td>
                    <td>Edit Info</td>
                </tr>
            </thead>
            <%
            for (Appointment a : appointments) {
                %>
                <tr>
                    <td><%= a.getPatientName() %></td>
                    <td><%= a.getDoctorName() %></td>
                    <td><%= a.getRoomNumber() %></td>
                    <td><%= a.getApptDate().toString() %></td>
                    <td><%= a.getApptType() %></td>
                    <td>
                        <form method="post" action="StaffServlet">
                            <input type="hidden" name="doctors" value="<%= request.getParameter("doctors") %>">
                            <input type="hidden" name="apptDate" value="<%= request.getParameter("apptDate") %>">
                            <input type="hidden" name="apptID" value="<%= a.getApptId() %>">
                            <input type="hidden" name="requestType" value="6">
                            <input type="submit" value="Edit Appointment">
                        </form>
                    </td>
                </tr>
                <%
            }
        %>
        </table>
        Number of matched records: <%= appointments.size() %>
    <p>
    <form method="post" action="StaffServlet">
    <input type="hidden" name="doctors" value="<%= request.getParameter("doctors") %>">
    <input type="hidden" name="apptTime" value="<%= request.getParameter("apptTime") %>">
    <input type="hidden" name="requestType" value="9">
    <input type="submit" value="New Appointment">
    </form>
    <p><a href="StaffServlet">Back to Staff Home</a>
    </body>
</html>
