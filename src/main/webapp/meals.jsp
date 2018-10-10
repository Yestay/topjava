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
<%--<TABLE border="1"--%>
<%--summary="This table gives some statistics about fruit--%>
<%--flies: average height and weight, and percentage--%>
<%--with red eyes (for both males and females).">--%>
<%--<CAPTION><EM>A test table with merged cells</EM></CAPTION>--%>
<%--<TR><TH rowspan="2"><TH colspan="2">Average--%>
<%--<TH rowspan="2">Red<BR>eyes--%>
<%--<TR><TH>height<TH>weight--%>
<%--<TR><TH>Males<TD>1.9<TD>0.003<TD>40%--%>
<%--<TR><TH>Females<TD>1.7<TD>0.002<TD>43%--%>
<%--</TABLE>--%>

<% List<Meal> meals = Arrays.asList(
        new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
        new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
        new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
        new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
        new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
        new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    // LocalTime start =
    List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(meals, LocalTime.MIDNIGHT, LocalTime.MAX, 2000);

%>
<table border="1">

    <%
        for (MealWithExceed mealW : mealWithExceeds) {
            String desc = mealW.getDescription();
            String date = mealW.getDateTime().toLocalDate().toString();
            boolean exceeded = mealW.isExceed();
    if ( mealW.isExceed()) {


    %>
    <tr style="background-color:red">
        <% } else {%>
    <tr >
    <% } %>

    <td><%=desc%> </td>
    <td><%=date%></td>
    <td><%=exceeded%></td>
    </tr>

    <%
        }
    %>
</table>


</body>
</html>