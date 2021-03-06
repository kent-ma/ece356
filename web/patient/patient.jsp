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
    Patient p = (Patient) getServletContext().getAttribute("patient");
%>
<!DOCTYPE html>
<link rel="stylesheet" href="css/github.css" type="text/css" />
<link rel="stylesheet" href="css/demo.css" type="text/css" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome, ________</title>
    </head>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="/scripts/jquery.min.js"></script>
    <script src="/bootstrap/js/bootstrap.min.js"></script>

    <div class="page-header">
        <h1>Patient Info</h1>
        <span class="label label-default">Freedom is how big your wallet is</span><hr>
        <div class="well well-small">Welcome! <% out.print(p.getName());%>
            <a href="Logout">Logout</a>
        </div>            
    </div>

    <div id="container">
        <div id="user_info">
            <form method="post" action="PatientServlet">
                Name: <%out.print(p.getName());%><br>
                S.I.N: <%out.print(p.getSin());%><br>
                Health Card No: <%out.print(p.getHealthCardNo());%><br>
                Phone Number: <input type="tel" name="phonenum" value="<%out.print(p.getPhoneNum());%>"><br>
                Address: <input type="text" name="addr" value="<%out.print(p.getAddress());%>"><br>
                Doctor: <%out.print(p.getDefDoctorName());%><br>
                <input type="hidden" name="requestType" value="1">
                <input type="submit" value="Update Information">
            </form>
        </div>
        <br>
        <div class="collapsible" id="appts"> List of Appointments <span></span></div>
        <div class="container">    
            <table border="1" style="width:800px">
                <%
                    List<Appointment> appts = (List<Appointment>) getServletContext().getAttribute("appts");
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
                    List<Prescription> ps = (List<Prescription>) getServletContext().getAttribute("prescriptions");
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
                    <td><%= pre.getPrescription()%></td>
                    <td><%= pre.getDoctor()%></td>
                    <td><%= pre.getDate()%></td>                     
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
