<%-- 
    Document   : login
    Created on : Mar 19, 2014, 12:32:37 PM
    Author     : kentma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login into HORSEPITAL</title>
    </head>
    <header><h1>Login</h1></header>
    <form method="POST" action="UserDataServlet">
        <br/>Username:<input type ="text" name="_username">
        <br/>Password:<input type="password" name="_password">
        <br/><input type="submit" value="Submit">
    </form>
</html>
