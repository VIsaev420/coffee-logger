package com.example.coffeelogger.bot.longpolling.persistance.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.example.coffeelogger.bot.longpolling.persistance.repository.CoffeeOrderRepository;
import com.example.coffeelogger.bot.longpolling.persistance.entity.Coffee;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CoffeeOrderAdapter {
    private final CoffeeOrderRepository repository;

    public void createOrder(Map<String, String> coffeeOrder, Long userId) {
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
        var lastOrder = repository.findFirstByCreateDateTimeBeforeAndUserIdOrderByCreateDateTimeDesc(LocalDateTime.now(), userId);
        repository.save(createCoffeeEntity(lastOrder));
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private static Coffee createCoffeeEntity(Map<String, String> coffeeOrder, Long userId) {
        var coffee = new Coffee();
        coffee.setCafe(coffeeOrder.get("CafeName"));
        coffee.setType(coffeeOrder.get("CoffeeType"));
        coffee.setPrice(Integer.parseInt(coffeeOrder.get("CoffeePrise")));
        coffee.setSize(Float.parseFloat(coffeeOrder.get("CoffeeSize")));
        coffee.setUserId(userId);
        return coffee;
    }

    private static Coffee createCoffeeEntity(Coffee oldEntity) {
        var newEntity = new Coffee();
        newEntity.setType(oldEntity.getType());
        newEntity.setSize(oldEntity.getSize());
        newEntity.setPrice(oldEntity.getPrice());
        newEntity.setCafe(oldEntity.getCafe());
        newEntity.setUserId(oldEntity.getUserId());
        return newEntity;
    }

    private LocalDateTime computeAfterDate(Long daysCount) {
        return LocalDateTime.now().minusDays(daysCount);
    }
}
