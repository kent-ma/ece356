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
        <title>Horsepital</title>
    </head>
    <body>
            <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
        
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        
        <center>
            <div class="page-header">
                <h1>Horsepital</h1>
                <span class="label label-default">Where the NSA monitors your history instead of your life</span>
            </div>

            <form method="post" action="Portal">
                Login ID: <input type="text" name="name"><br>
                Password: <input type="password" name="password"><br>
                <input type="submit" value="Login">
            </form>

            <a href="doctor/doctor.jsp">Doctor</a><n/>
            <a href="financial/financial.jsp">Financial</a>
            <a href="legal/legal.jsp">Legal</a>
            <a href="staff/staff.jsp">Staff</a>
            <a href="patient/patient.jsp">Patient</a>

            <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
            <!-- Include all compiled plugins (below), or include individual files as needed -->
            <script src="js/bootstrap.min.js"></script>
        </center>    
    </body>
</html>

</html>
