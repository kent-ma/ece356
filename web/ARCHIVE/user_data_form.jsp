<%-- 
    Document   : user_data_form
    Created on : Jan 22, 2014, 2:05:15 PM
    Author     : kentma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <header><h1>User Data Form</h1></header>
    <form method="post" action="UserDataServlet">
        What's your name? <input type="text" name="username"><br>
        What's your favorite colour?
        <select name="favColor">
            <option value="Red">red</option>
            <option value="Blue">blue</option>
            <option value="Black">black</option>
            <option value="Green">green</option>
        </select><br>
        <input type="submit" value="Submit Query">
    </form>
</html>
