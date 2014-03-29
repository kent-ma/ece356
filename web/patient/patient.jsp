<%-- 
    Document   : patient
    Created on : Mar 28, 2014, 11:29:21 AM
    Author     : kentma
--%>

<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ece356.Members.Patient"%>
<%@page import="ece356.Members.Appointment"%>
<%@page import="ece356.Members.Prescription"%>
<%@page import="java.util.List"%>

<%
    Patient p = (Patient) request.getAttribute("patient");
%>
<!DOCTYPE html>
<link rel="stylesheet" href="css/github.css" type="text/css" />
<link rel="stylesheet" href="css/demo.css" type="text/css" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome, ________</title>
    </head>
    <div id="container">
        <div id="welcome"><h1>Welcome <% out.print(p.getName());%></h1></div><br>
        <div id="user_info">
            <form>
                Name: <%out.print(p.getName());%><br>
                S.I.N: <%out.print(p.getSin());%><br>
                Health Card No: <%out.print(p.getHealthCardNo());%><br>
                Phone Number: <input type="tel" name="phonenum" value="<%out.print(p.getPhoneNum());%>"><br>
                Address: <input type="text" name="addr" value="<%out.print(p.getAddress());%>"><br>
                Doctor: <%out.print(p.getDefDoctorName());%><br>
                Old Password: <input type="password" name="oldpassword"><br>
                New Password: <input type="password" name="newpassworda"><br>
                Confirm New Password: <input type="password" name="newpasswordb"><br>
                <input type ="hidden" name="requestType" value="1">
                <input type="submit" value="Edit Data">
            </form>
        </div>
        <br>
        <div class="collapsible" id="appts"> List of Appointments <span></span></div>
        <div class="container">    
            <table border="1" style="width:800px">
                <%
                    List<Appointment> appts = (List<Appointment>) request.getAttribute("appts");
                    if (!appts.isEmpty()) {
                %>
                <tr>
                    <td>Doctor</td>
                    <td>Room No.</td>
                    <td>Date</td>
                    <td>Type</td>
                </tr>
                <%
                    for (Appointment a : appts) {
                %>
                <tr>
                    <td><%= a.getDoctorName()%></td>
                    <td><%= a.getRoomNumber()%></td>
                    <td><%= a.getApptDate()%></td>
                    <td><%= a.getApptType()%></td>                         
                </tr>
                <%
                    }
                    }
                %>
            </table>
        </div>
        <div class="collapsible" id="prescriptions"> List of Prescriptions<span></span></div>
        <div class="container">
            <table border="1" style="width:800px">
                <%
                    List<Prescription> ps = (List<Prescription>) request.getAttribute("prescriptions");
                    if (!ps.isEmpty()) {
                %>
                <tr>
                    <td>Prescription</td>
                    <td>Doctor</td>
                    <td>Date</td>
                    
                </tr>
                <%
                    for (Prescription pre : ps) {
                %>
                <tr>
                    <td><%= pre.getPrescription() %></td>
                    <td><%= pre.getDoctor() %></td>
                    <td><%= pre.getDate() %></td>                     
                </tr>
                <%
                    }
                    }
                %>
            </table>
        </div>
    </div>
</html>

<!-- JavaScript -->

<script type="text/javascript" src="javascript/jquery.cookie.js"></script>
<script type="text/javascript" src="javascript/jquery.collapsible.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        //collapsible management
        $('.collapsible').collapsible({
            defaultOpen: 'appts,prescriptions'
        });
    });
</script>
