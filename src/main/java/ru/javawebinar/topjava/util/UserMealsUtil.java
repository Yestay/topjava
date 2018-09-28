package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> l = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        l.forEach(a-> System.out.println(a.getDescription()+" "+ a.getDateTime()+" "+a.isExceed()));
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field


        List<UserMeal> listFiltered = new ArrayList<>();
        Map<LocalDate, Integer> mapCaloriesByDay = new HashMap<>();

        for (UserMeal x : mealList) {
            // Collect caloriesByDay
            mapCaloriesByDay.merge(x.getDateTime().toLocalDate(), x.getCalories(), (a, b) -> a + b);

            // Apply filter startTime and endTime
            if ((x.getDateTime().toLocalTime().isAfter(startTime)) && x.getDateTime().toLocalTime().isBefore(endTime))
                listFiltered.add(x);
        }


        List<UserMealWithExceed> result = new ArrayList<>();
        for (UserMeal x : listFiltered// Run through the filtered list
        ) {
            //Create a new instance of UserMealWithExceed
            UserMealWithExceed userMealWithExceed = new UserMealWithExceed(x.getDateTime(),
                    x.getDescription(), x.getCalories(),
                    mapCaloriesByDay.get(x.getDateTime().toLocalDate()) > caloriesPerDay);

            result.add(userMealWithExceed);//add the newly created object to result list
        }
           return result;
    }
}
