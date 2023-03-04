package ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.repository.CoffeeOrderRepository;
import ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.entity.Coffee;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CoffeeOrderAdapter {
    private final CoffeeOrderRepository repository;

    public void createOrder(HashMap<String, String> coffeeOrder, Long userId) {
        var coffee = createCoffeeEntity(coffeeOrder, userId);
        repository.save(coffee);
    }

    public List<Coffee> getWeekStats(Long userId) {
        return repository.findCoffeesByUserIdAndCreateDateTimeAfter(userId, computeAfterDate(7L));
    }

    public List<Coffee> getMontStats(Long userId) {
        return repository.findCoffeesByUserIdAndCreateDateTimeAfter(userId, computeAfterDate(31L));
    }

    public void repeatLastOrder(Long userId) {

    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private static Coffee createCoffeeEntity(HashMap<String, String> coffeeOrder, Long userId) {
        var coffee = new Coffee();
        coffee.setCafe(coffeeOrder.get("CafeName"));
        coffee.setType(coffeeOrder.get("CoffeeType"));
        coffee.setPrice(Integer.parseInt(coffeeOrder.get("CoffeePrise")));
        coffee.setSize(Float.parseFloat(coffeeOrder.get("CoffeeSize")));
        coffee.setUserId(userId);
        return coffee;
    }

    private LocalDateTime computeAfterDate(Long daysCount) {
        return LocalDateTime.now().minusDays(daysCount);
    }
}
