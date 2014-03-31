<%-- 
    Document   : staff
    Created on : Mar 28, 2014, 11:29:26 AM
    Author     : kentma
--%>

<%@page import="java.util.List"%>
<%@page import="ece356.Members.Doctor"%>
<%@page import="ece356.Members.Login"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>STAFF - Home</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.9.1.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <script src="/bootstrap/js/jquery-ui-timepicker-addon.js"></script>
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script>
        $(function() {
          $( ".datepicker" ).datepicker();
        });
        $(function() {
          $( ".timepicker" ).timepicker();
        });
        </script>
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

        <p><b>Get Patient Info</b>
        <form method="post" action="StaffServlet">
            Patient Name: <input type="text" name="patientName"><br>
            Patient OHIP: <input type="text" name="patientOHIP"><br>
            <input type="hidden" name="requestType" value="1">
            <input type="submit" value="Search">
        </form>
        <p><b>Get Appointment Info</b>
        <form method="post" action="StaffServlet">
            Appt Time: <input type="text" name="apptDate" class="datepicker"><br>
            Doctor Name: <select name="doctors"><% List<Doctor> doctors = (List<Doctor>)getServletContext().getAttribute("doctorlist"); %>
            <% for (Doctor d : doctors) { %>
            <option value="<%= d.getDoctorId() %>"><%= d.getName() %></option>
            <% } %>
            </select><br>
            <input type="hidden" name="requestType" value="2">
            <input type="submit" value="Search">
        </form>
    </body>
</html>
