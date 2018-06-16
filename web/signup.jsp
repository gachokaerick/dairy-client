<%--
    Document   : signup
    Created on : May 13, 2018, 10:52:05 PM
    Author     : enrico
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <h1>Please fill in your information below</h1>
        <div>
            <c:if test="${requestScope.error != null}">
                <p>${requestScope.error}</p>
            </c:if>
            <c:if test="${requestScope.message != null}">
                <p>${requestScope.message}</p>
            </c:if>
        </div>
        <form action="signup" method="POST">
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
