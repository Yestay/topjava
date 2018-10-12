<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ru.javawebinar.topjava.model.Meal" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.Month" %>
<%@ page import="ru.javawebinar.topjava.util.MealsUtil" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="ru.javawebinar.topjava.model.MealWithExceed" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<table border="1">
    <c:forEach var="num" items="${list}">
        <c:if test="${num.isExceed()}" >
        <tr style="background-color:red">
        </c:if>
        <c:if test ="${!num.isExceed()}" >
        <tr>
        </c:if>
        <td>${num.getDescription()}</td>
            <td>${num.getDateTime()} </td>
            <td>${num.isExceed()}</td>
        <javatime:parseLocalDateTime value="${num.getDateTime()}" pattern="yyyy-MM-dd" var="parsedDate" />
        <td>${parsedDate}</td>
        </tr>
    </c:forEach>
</table>


</body>
</html>