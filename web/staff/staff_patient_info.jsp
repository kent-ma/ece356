<%-- 
    Document   : staff
    Created on : Mar 28, 2014, 11:29:26 AM
    Author     : kentma
--%>

<%@page import="ece356.Members.Doctor"%>
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
            List<Patient> patients = (List<Patient>)request.getAttribute("record");
            %>
            <thead>
            <tr>
                <td>Name</td>
                <td>Address</td>
                <td>Phone Number</td>
                <td>DOB</td>
                <td>SIN</td>
                <td>OHIP Number</td>
                <td>Status</td>
                <td>Assign to Doctor</td>
                <td>Edit Info</td>
            </tr>
            </thead>
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
                    <td>
                        <form method="post" action="StaffServlet">
                            <select name="doctors"><% List<Doctor> doctors = (List<Doctor>)getServletContext().getAttribute("doctorlist"); %>
                            <% for (Doctor d : doctors) { %>
                                <option value="<%= d.getDoctorId() %>"><%= d.getName() %></option>
                            <% } %>
                            </select>
                            <input type="hidden" name="patientName" value="<%= request.getParameter("patientName") %>">
                            <input type="hidden" name="patientOHIP" value="<%= request.getParameter("patientOHIP") %>">
                            <input type="hidden" name="patientID" value="<%= p.getPatientId() %>">
                            <input type="hidden" name="requestType" value="5">
                            <input type="submit" value="Assign">
                        </form>
                    </td>
                    <td>
                        <form method="post" action="StaffServlet">
                            <input type="hidden" name="patientName" value="<%= request.getParameter("patientName") %>">
                            <input type="hidden" name="patientOHIP" value="<%= request.getParameter("patientOHIP") %>">
                            <input type="hidden" name="patientID" value="<%= p.getPatientId() %>">
                            <input type="hidden" name="requestType" value="3">
                            <input type="submit" value="Edit Info">
                        </form>
                    </td>
                </tr>
                <%
            }
        %>
        </table>
        Number of matched records: <%= patients.size() %>
    <p>
    <form method="post" action="StaffServlet">
    <input type="hidden" name="patientName" value="<%= request.getParameter("patientName") %>">
    <input type="hidden" name="patientOHIP" value="<%= request.getParameter("patientOHIP") %>">
    <input type="hidden" name="requestType" value="8">
    <input type="submit" value="New Patient">
    </form>
    <p><a href="/ece356/StaffServlet">Back to Staff Home</a>
    </body>
</html>
