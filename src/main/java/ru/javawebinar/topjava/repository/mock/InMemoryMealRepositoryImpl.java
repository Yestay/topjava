package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        Integer userTMP = 1;
        MealsUtil.MEALS.forEach(meal -> save(userTMP, meal));
    }

    @Override
    public Meal save(Integer userId, Meal meal) {
        Map<Integer, Meal> userMeal = repository.get(userId);
        if (userMeal == null) userMeal = new ConcurrentHashMap<>();
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            userMeal.put(meal.getId(), meal);
            repository.put(userId, userMeal);
            return meal;
        }
        // treat case: update, but absent in storage
        Meal mealUpdated = userMeal.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        repository.put(userId, userMeal);
        return mealUpdated;
    }

    @Override
    public boolean delete(Integer userId, int id) {
        return repository.get(userId).remove(id) == null ? false : true;
    }

    @Override
    public Meal get(Integer userId, int id) {
        return repository.get(userId).get(id);//3.2: если по запрошенному id еда отсутствует или чужая, возвращать null/false
    }

    @Override
    public List<Meal> getAll(Integer userId) {
        //3.3: список еды возвращать отсортированный в обратном порядке по датам
//        return repository.get(userId).values().stream().sorted(Comparator.comparing(meal::get(meal1 , meal2) -> )).collect(Collectors.toList());
        Map<Integer, Meal> integerMealMap = repository.get(userId);
        return integerMealMap == null ? new ArrayList<Meal>() : integerMealMap.values().stream().sorted(Comparator.comparing(meal -> meal.getDate(), Comparator.reverseOrder())).collect(Collectors.toList());
    }
}

