<%--
    Document   : applyContract
    Created on : May 17, 2018, 9:37:49 PM
    Author     : enrico
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="jquery-ui-1.12.1.custom/jquery-ui.min.css">
        <script src="jquery-ui-1.12.1.custom/external/jquery/jquery.js"></script>
        <script src="jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contract Application</title>
    </head>
    <body>
        <h1>Enter contract details below</h1>
        <div>
            <c:if test="${requestScope.error != null}">
                <p>${requestScope.error}</p>
            </c:if>
            <c:if test="${requestScope.message != null}">
                <p>${requestScope.message}</p>
            </c:if>
        </div>
        <form action="getContracts" method="POST">
            Start date: <input type="date" name="start" required id="start"><br>
            End date: <input type="date" name="end" required id="end"><br>
            Amount/day: <input type="number" name="amount" required><br>
            Cost/litre: <input type="number" name="cost" required><br>
            <input id="submitApplication" type="submit" value="Submit">
        </form>
    </body>
</html>
