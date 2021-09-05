package ru.lanit.bpm.coffeelogger.bot.longpolling;

import org.springframework.stereotype.Component;
import ru.lanit.bpm.coffeelogger.bot.longpolling.repository.OperatorRepository;
import ru.lanit.bpm.coffeelogger.bot.longpolling.repository.entity.Operator;

@Component
public class OperatorSaver {
    private static OperatorRepository repository;

    public OperatorSaver(OperatorRepository repository) {
        OperatorSaver.repository = repository;
    }

    public static void save(Operator operator){
        repository.save(operator);
    }
}
