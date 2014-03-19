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
    <header><h1>User Data Form</h1></header>
    <form method="POST" action="UserDataServlet">
        <input type ="text" name="_username">
        <input type="password" name="_password">
        <input type="submit" value="Submit Query">
    </form>
</html>
