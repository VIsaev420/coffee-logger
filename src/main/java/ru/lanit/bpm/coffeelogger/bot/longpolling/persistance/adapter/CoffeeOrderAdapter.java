package ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.repository.CoffeeOrderRepository;
import ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.entity.Coffee;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class CoffeeOrderAdapter {
    private final CoffeeOrderRepository repository;

    public void createOrder(HashMap<String, String> coffeeOrder, Long userId) {
        var coffee = createCoffeeEntity(coffeeOrder, userId);
        save(coffee);
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private void save(Coffee coffeeOrder) {
        repository.save(coffeeOrder);
    }

    private static Coffee createCoffeeEntity(HashMap<String, String> coffeeOrder, Long userId) {
        var coffee = new Coffee();
        coffee.setCafe(coffeeOrder.get("CafeName"));
        coffee.setType(coffeeOrder.get("CoffeeType"));
        coffee.setPrice(Integer.parseInt(coffeeOrder.get("CoffeePrise")));
        coffee.setSize(Float.parseFloat(coffeeOrder.get("CoffeeSize")));
        coffee.setUserId(userId);
        return coffee;
    }
}
