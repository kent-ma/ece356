<%-- 
    Document   : patient
    Created on : Mar 28, 2014, 11:29:21 AM
    Author     : kentma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ece356.Members.Patient"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome, ________</title>
    </head>
    <div id="container">
        <div id="welcome"><h1>
                <%
                    Patient p = (Patient) request.getAttribute("patient");
                    out.print("Welcome " + p.getName());
                %>
            </h1></div>
        <div id="user_info">
            <body>This is the user information so STFU</body>
        </div>
        <div id="appts">
            <body>GET OUT OF MY APPOINTMENTS</body>
        </div>
        <div id="perscriptions"><body>GET OUT OF MY PERSCRIPTIONS</body></div>
    </div>
</html>
