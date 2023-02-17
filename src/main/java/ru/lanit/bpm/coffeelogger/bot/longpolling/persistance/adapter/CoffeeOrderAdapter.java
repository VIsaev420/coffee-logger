package ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.repository.CoffeeOrderRepository;
import ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.entity.Coffee;
import ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.entity.Operator;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class CoffeeOrderAdapter {
    private final CoffeeOrderRepository repository;

    public Coffee createOrder(HashMap<String, String> coffeeOrder, Operator operator){
        var coffee = new Coffee();
        coffee.setCafe(coffeeOrder.get("CafeName"));
        coffee.setType(coffeeOrder.get("CoffeeType"));
        coffee.setPrice(Integer.parseInt(coffeeOrder.get("CoffeePrise")));
        coffee.setSize(Float.parseFloat(coffeeOrder.get("CoffeeSize")));
        coffee.setOperator(operator);

        save(coffee);
        return coffee;
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private void save(Coffee coffeeOrder){
        repository.save(coffeeOrder);
    }
}
