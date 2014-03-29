<%-- 
    Document   : patient
    Created on : Mar 28, 2014, 11:29:21 AM
    Author     : kentma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ece356.Members.Patient"%>
<%
    Patient p = (Patient) request.getAttribute("patient");
%>
<!DOCTYPE html>
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
                Doctor: <%out.print(p.getDefDoctorId());%><br>
                <input type="submit" value="Submit Query">
            </form>
        </div>
        <br>
        <div id="appts">
            <body>GET OUT OF MY APPOINTMENTS</body>
        </div>
        <div id="perscriptions"><body>GET OUT OF MY PERSCRIPTIONS</body></div>
    </div>
</html>
