package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MealServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Hello");
        //request.setAttribute("name","hi");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println("<H1>Hello, world! или Привет мир</H1>");
       // response.sendRedirect("meals.jsp");
        request.getRequestDispatcher("/meals.jsp").forward(request, response);


        pw.println("<B>Список групп</B>");
        pw.println("<table border=1>");

        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        try {
            for (Meal meal : meals) {
                pw.println("<tr>");
                pw.println("<td color=red>" + meal.getDate()+ "</td>");
                pw.println("<td>" + meal.getDescription() + "</td>");
                pw.println("<td>" +meal.getDateTime() + "</td>");
                pw.println("<td>" + meal.getCalories() + "</td>");
                pw.println("</tr>");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        pw.println("</table>");

    }
}
