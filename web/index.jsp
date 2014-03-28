<%-- 
    Document   : index
    Created on : Jan 22, 2014, 2:16:00 PM
    Author     : kentma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body bgcolor="#E6E6FA">
    <img src="Resources/horsepital_banner.jpg" height="90" width="720">
    <form method="post" action="LoginServlet">
        Login ID: <input type="text" name="name"><br>
        Password: <input type="text" name="password"><br>
        <input type="submit" value="Login">
    </form>
    
    <a href="Doctor/doctor.jsp">Doctor</a><n/>
    <a href="financial.jsp">Financial</a>
    </body>
</html>

</html>
