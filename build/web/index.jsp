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
        
        <center>
            <div class="page-header">
                <h1>Horsepital</h1>
                <span class="label label-default">Where the NSA monitors your history instead of your life</span>
            </div>
        
        
            <form class="form-horizontal" role="form" method="post" action="Portal">
                <div class="form-group">
                   <label for="name" class="col-sm-4 control-label">Login ID</label>
                   <div class="col-sm-5">
                      <input type="text" class="form-control" name="name" 
                         placeholder="Enter Login ID">
                   </div>
                </div>
                <div class="form-group">
                   <label for="password" class="col-sm-4 control-label">Last Name</label>
                   <div class="col-sm-5">
                      <input type="password" class="form-control" name="password" 
                         placeholder="Enter Password">
                   </div>
                </div>
                <div class="form-group">
                   <div class="col-sm-offset-1 col-sm-10">
                      <button type="submit" class="btn btn-default" value="Login">Login</button>
                   </div>
                </div>
            </form>
            <text>Copyright of Horsepital INC.</text><br>
            <a href="doctor/doctor.jsp">Doctor</a><n/>
            <a href="financial.jsp">Financial</a>
            <a href="legal/legal.jsp">Legal</a>
            <a href="staff/staff.jsp">Staff</a>
            <a href="patient/patient.jsp">Patient</a>
        </center>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>

</html>
