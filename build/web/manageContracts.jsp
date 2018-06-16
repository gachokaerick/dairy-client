<%--
    Document   : manageContracts
    Created on : May 19, 2018, 5:33:44 PM
    Author     : enrico
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contract Management</title>
        <link rel="stylesheet" href="css/manageContracts.css">
    </head>
    <body>
        <form action="getContracts" method="GET">
            <input type="submit" name="approved" value="View Approved">
            <input type="submit" name="pending" value="View Pending">
            <input type="submit" name="denied" value="View Denied">
        </form>
        <table border="2">
            <thead>
                <tr>
                    <td></td>
                    <td><b>Supplier Id<b></td>
                                <td><b>Start Date</b></td>
                                <td><b>End Date</b></td>
                                <td><b>Liters Per Day</b></td>
                                <td><b>Cost Per Liter</b></td>
                                <td><b>Status</b></td>
                                <td><b>Actions</b></td>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${sessionScope.list  != null}">
                                        <c:forEach items="${sessionScope.list}" var="contract" varStatus="loop">
                                            <c:if test="${contract.status eq sessionScope.requiredStatus}">
                                                <tr>
                                                    <td class="counterCell"></td>
                                                    <td>${contract.supplierId}</td>
                                                    <td>${contract.startDate}</td>
                                                    <td>${contract.endDate}</td>
                                                    <td>${contract.amountPerDay}</td>
                                                    <td>${contract.unitCost}</td>
                                                    <td>${contract.status}</td>
                                                    <td>
                                                        <form action="editContracts" method="POST">
                                                            <input type="hidden" name="contract" value="${loop.count - 1}">
                                                            <c:if test="${contract.status ne 'approved'}">
                                                                <input type="submit" value="approve" name="approve">
                                                            </c:if>
                                                            <c:if test="${contract.status ne 'denied'}">
                                                                <input type="submit" value="deny" name="deny">
                                                            </c:if>
                                                            <c:if test="${contract.status eq 'denied'}">
                                                                <input type="submit" value="delete" name="delete">
                                                            </c:if>
                                                        </form>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </tbody>
                                </table> 
                                </body>
                                </html>
