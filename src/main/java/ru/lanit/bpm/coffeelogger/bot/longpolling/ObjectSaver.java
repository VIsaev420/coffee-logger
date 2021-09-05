package ru.lanit.bpm.coffeelogger.bot.longpolling;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.lanit.bpm.coffeelogger.bot.longpolling.repository.CoffeeOrderRepository;
import ru.lanit.bpm.coffeelogger.bot.longpolling.repository.LogRepository;
import ru.lanit.bpm.coffeelogger.bot.longpolling.repository.entity.Coffee;
import ru.lanit.bpm.coffeelogger.bot.longpolling.repository.entity.Log;

import java.util.ArrayList;

@Component
public class ObjectSaver {
    private CoffeeOrderRepository coffeeOrderRepository;

    @SneakyThrows
    public void save(ArrayList<Object> values){

        var logObject = new Log();
        var objectMapper = new ObjectMapper();
        var coffeeDbObject = new Coffee();
        coffeeDbObject.setType(objectMapper.writeValueAsString(values.get(0)));
//        coffeeDbObject.setSize(objectMapper.writeValue(values.get(2)));
//        coffeeDbObject.setPrice(values.get(3));
//        coffeeDbObject.setCafe(objectMapper.writeValueAsString(values.get(3)));
        coffeeOrderRepository.save(coffeeDbObject);
    }
}
