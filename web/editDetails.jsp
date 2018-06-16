<%--
    Document   : editDetails
    Created on : May 19, 2018, 3:54:34 PM
    Author     : enrico
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Your Details</title>
    </head>
    <body>
        <form action="editDetails" method="POST">
            First Name: <input type="text" name="first-name" required><br>
            Last Name: <input type="text" name="last-name" required><br>
            National Id: <input type="number" name="national-id" required><br>
            Email: <input type="email" name="email" required><br>
            Phone: <input type="number" name="phone" required><br>
            Address: <input type="text" name="address" required><br>
            Password <input type="password" name="password" required><br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
