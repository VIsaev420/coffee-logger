package com.example.coffeelogger.bot.longpolling.adapter;

import com.example.coffeelogger.bot.longpolling.persistance.entity.Coffee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import support.AbstractTestSupport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CoffeeOrderAdapterTest extends AbstractTestSupport {

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void createOrderTest() {
        coffeeOrderAdapter.createOrder(createOrderAttrs(""), 1L);

        var result = repository.findAll();

        assertEquals(1, result.size());
        assertEquals("капуч", result.get(0).getType());
    }

    //todo actualize
    void repeatOrderTest() {
        var first = createCoffeeEntity();
        first.setCreateDateTime(LocalDateTime.of(
                LocalDate.of(2023, 3, 5),
                LocalTime.MIDNIGHT));
        var second = createCoffeeEntity();
        second.setCreateDateTime(
                LocalDateTime.of(
                        LocalDate.of(2023, 3, 4),
                        LocalTime.MIDNIGHT)
        );
        var last = createCoffeeEntity();
        last.setCreateDateTime(
                LocalDateTime.of(
                        LocalDate.of(2023, 2, 4),
                        LocalTime.MIDNIGHT)
        );
        repository.saveAll(List.of(first, last));
        var entity = repository.findAll().get(0);

        coffeeOrderAdapter.repeatLastOrder(420L);

        var entityList = repository.findAll();
        assertEquals(2, entityList.size());
        assertEquals(entity.getType(), entityList.get(1).getType());
        assertNotEquals(entity.getId(), entityList.get(1).getId());
    }

    private static Coffee createCoffeeEntity() {
        var entity = new Coffee();
        entity.setType("капуч");
        entity.setSize(0.4F);
        entity.setPrice(1234);
        entity.setCafe("skr");
        entity.setUserId(420L);
        return entity;
    }

    private Map<String, String> createOrderAttrs(String postfix) {
        return Map.of(
                "CafeName", "Scuratov" + postfix,
                "CoffeeType", "капуч" + postfix,
                "CoffeePrise", "123",
                "CoffeeSize", "0.3"
        );
    }
}
